package com.cafe24.exchange.domain.currency;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="currency_id")
    private Long Id;
    private String source;
    @Column(length = 6)
    private String nation;
    private Double exchangeRate;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date regDateTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)  // java.util.Date이므로 @Temporal을 붙여준다.
    @Column(name = "last_modified_at", updatable = true)
    private Date lastModifiedDateTime;


}
