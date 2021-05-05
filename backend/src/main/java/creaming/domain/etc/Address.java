package creaming.domain.etc;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Address {

    private String address;
    private String phoneNumber;
    private String name;
}
