package creaming.domain.etc;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class Address {

    private String address;
    private String phoneNumber;
    private String name;
    private String zoneCode;

}
