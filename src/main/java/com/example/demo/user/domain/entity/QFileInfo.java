package com.example.demo.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileInfo is a Querydsl query type for FileInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileInfo extends EntityPathBase<FileInfo> {

    private static final long serialVersionUID = 832609972L;

    public static final QFileInfo fileInfo = new QFileInfo("fileInfo");

    public final NumberPath<Integer> boardSeq = createNumber("boardSeq", Integer.class);

    public final StringPath fileExt = createString("fileExt");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> fileSeq = createNumber("fileSeq", Integer.class);

    public final StringPath fileSize = createString("fileSize");

    public final StringPath originalFileName = createString("originalFileName");

    public final StringPath regId = createString("regId");

    public final StringPath storedFileName = createString("storedFileName");

    public final StringPath useYn = createString("useYn");

    public QFileInfo(String variable) {
        super(FileInfo.class, forVariable(variable));
    }

    public QFileInfo(Path<? extends FileInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileInfo(PathMetadata metadata) {
        super(FileInfo.class, metadata);
    }

}

