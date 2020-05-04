import "../css/map.css";
import "ol/ol.css";
import "ol-layerswitcher/src/ol-layerswitcher.css";
import "ol-contextmenu/dist/ol-contextmenu.css";
// import * as ol from 'ol'
import Map from 'ol/Map';
import View from 'ol/View';
import Feature from 'ol/Feature';
import SourceOSM from 'ol/source/OSM';
import SourceVector from 'ol/source/Vector';
import {Fill, Icon, Stroke, Style, Text} from 'ol/style';
import {Tile, Vector} from 'ol/layer';
import {transform} from 'ol/proj';
import {format} from 'ol/coordinate';
import ContextMenu from "ol-contextmenu"
import Point from "ol/geom/Point";

const view = new View(
    {center: [14139375.266574217, 4507391.386530381], zoom: 6}),
    vectorLayer = new Vector({source: new SourceVector()}),
    baseLayer = new Tile({source: new SourceOSM()}),
    map = new Map({
      target: 'map',
      view: view,
      layers: [baseLayer, vectorLayer],
    });

const contextmenu_items = [
  {
    text: 'Center map here',
    classname: 'bold',
    icon: 'images/center.png',
    callback: center,
  },
  {
    text: 'Some Actions',
    icon: 'images/view_list.png',
    items: [
      {
        text: 'Center map here',
        icon: 'images/center.png',
        callback: center,
      },
      {
        text: 'Add a Marker',
        icon: 'images/pin_drop.png',
        callback: marker,
      },
    ],
  },
  {
    text: 'Add a Marker',
    icon: 'images/pin_drop.png',
    callback: marker,
  },
  '-', // this is a separator
];

var removeMarkerItem = {
  text: 'Remove this Marker',
  classname: 'marker',
  callback: removeMarker,
};

var contextmenu = new ContextMenu({
  width: 180,
  items: contextmenu_items,
});
map.addControl(contextmenu);

contextmenu.on('open', function (evt) {
  var feature = map.forEachFeatureAtPixel(evt.pixel, function (ft, l) {
    return ft;
  });
  if (feature && feature.get('type') === 'removable') {
    contextmenu.clear();
    removeMarkerItem.data = {
      marker: feature,
    };
    contextmenu.push(removeMarkerItem);
  } else {
    contextmenu.clear();
    contextmenu.extend(contextmenu_items);
    contextmenu.extend(contextmenu.getDefaultItems());
  }
});

map.on('pointermove', function (e) {
  var pixel = map.getEventPixel(e.originalEvent);
  var hit = map.hasFeatureAtPixel(pixel);

  if (e.dragging) {
    return;
  }

  map.getTargetElement().style.cursor = hit ? 'pointer' : '';
});

// from https://github.com/DmitryBaranovskiy/raphael
function elastic(t) {
  return (
      Math.pow(2, -10 * t) * Math.sin(((t - 0.075) * (2 * Math.PI)) / 0.3) + 1
  );
}

function center(obj) {
  view.animate({
    duration: 700,
    easing: elastic,
    center: obj.coordinate,
  });
}

function removeMarker(obj) {
  vectorLayer.getSource().removeFeature(obj.data.marker);
}

function marker(obj) {
  var coord4326 = transform(obj.coordinate, 'EPSG:3857', 'EPSG:4326'),
      template = 'Coordinate is ({x} | {y})',
      iconStyle = new Style({
        image: new Icon({scale: 0.6, src: 'images/pin_drop.png'}),
        text: new Text({
          offsetY: 25,
          text: format(coord4326, template, 2),
          font: '15px Open Sans,sans-serif',
          fill: new Fill({color: '#111'}),
          stroke: new Stroke({color: '#eee', width: 2}),
        }),
      }),
      feature = new Feature({
        type: 'removable',
        geometry: new Point(obj.coordinate),
      });

  feature.setStyle(iconStyle);
  vectorLayer.getSource().addFeature(feature);
}

export {map};