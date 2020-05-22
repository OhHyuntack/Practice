package com.example.demo.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1400285372L;

    public static final QBoard board = new QBoard("board");

    public final StringPath boardPW = createString("boardPW");

    public final NumberPath<Integer> boardSeq = createNumber("boardSeq", Integer.class);

    public final StringPath boardType = createString("boardType");

    public final StringPath contact = createString("contact");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> delDate = createDateTime("delDate", java.time.LocalDateTime.class);

    public final StringPath department = createString("department");

    public final ListPath<FileInfo, QFileInfo> fileInfoList = this.<FileInfo, QFileInfo>createList("fileInfoList", FileInfo.class, QFileInfo.class, PathInits.DIRECT2);

    public final StringPath isDel = createString("isDel");

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> readCnt = createNumber("readCnt", Integer.class);

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

