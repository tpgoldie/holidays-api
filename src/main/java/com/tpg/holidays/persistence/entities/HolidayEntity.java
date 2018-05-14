package com.tpg.holidays.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "holidays")
@GenericGenerator(
        name = "seq_gen",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "holidays_seq"),
            @Parameter(name = "initial_value", value = "1000"),
            @Parameter(name = "increment_size", value = "1")
        }
)
@Getter
@Setter
public class HolidayEntity extends BaseEntity {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Column(name = "reasons_to_visit")
    private String reasonsToVisit;

    @Column(name = "available_from")
    @NotNull
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime availableFrom;

    @Column(name = "available_to")
    @NotNull
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime availableTo;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    @NotNull
    private DestinationEntity destination;
}
