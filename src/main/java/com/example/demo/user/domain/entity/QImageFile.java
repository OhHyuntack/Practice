package com.example.demo.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageFile is a Querydsl query type for ImageFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QImageFile extends EntityPathBase<ImageFile> {

    private static final long serialVersionUID = 499305549L;

    public static final QImageFile imageFile = new QImageFile("imageFile");

    public final StringPath fileExt = createString("fileExt");

    public final StringPath filePath = createString("filePath");

    public final StringPath fileSize = createString("fileSize");

    public final NumberPath<Integer> imageFileSeq = createNumber("imageFileSeq", Integer.class);

    public final StringPath originalFileName = createString("originalFileName");

    public final StringPath regId = createString("regId");

    public final StringPath storedFileName = createString("storedFileName");

    public final StringPath useYn = createString("useYn");

    public QImageFile(String variable) {
        super(ImageFile.class, forVariable(variable));
    }

    public QImageFile(Path<? extends ImageFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageFile(PathMetadata metadata) {
        super(ImageFile.class, metadata);
    }

}

