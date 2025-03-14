package org.koreait.board.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.koreait.global.entities.BaseMemberEntity;

import java.io.Serializable;

@Data
@Entity
@Table(indexes = @Index(name = "idx_comment_data_created_at", columnList = "createdAt ASC"))
public class CommentData extends BaseMemberEntity implements Serializable {
    @Id @GeneratedValue // 기본키(Primary Key) 자동생성
    private Long seq;

    @JsonIgnore // DB추가시엔 필요할꺼같지만....
    @ManyToOne(fetch = FetchType.LAZY)
    private BoardData data;

    @Column(length=40, nullable = false)
    private String commenter;

    @Column(length=65)
    private String guestPw;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(length=20)
    private String ipAddr;

    @Column(length=150)
    private String userAgent;

    @Transient
    private boolean editable; // 댓글 수정, 삭제 가능 여부
}