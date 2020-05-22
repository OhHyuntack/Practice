package com.example.demo.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTime is a Querydsl query type for Time
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QTime extends EntityPathBase<Time> {

    private static final long serialVersionUID = -647035337L;

    public static final QTime time = new QTime("time");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public QTime(String variable) {
        super(Time.class, forVariable(variable));
    }

    public QTime(Path<? extends Time> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTime(PathMetadata metadata) {
        super(Time.class, metadata);
    }

}

