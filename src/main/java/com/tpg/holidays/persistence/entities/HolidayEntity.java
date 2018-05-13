package com.tpg.holidays.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "holidays")
@GenericGenerator(
        name = "holidays_seq_gen",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "seq_holidays"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
        }
)
@Getter
@Setter
public class HolidayEntity {

    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="holidays_seq_gen")
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Column(name = "reasons_to_visit")
    private String reasonsToVisit;

    @Column(name = "available_from")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime availableFrom;

    @Column(name = "available_to")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime availableTo;
}
