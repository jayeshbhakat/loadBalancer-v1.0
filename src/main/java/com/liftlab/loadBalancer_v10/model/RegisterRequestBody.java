package com.liftlab.loadBalancer_v10.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequestBody {

    private String ipAddress;
    private String serviceId;

}
