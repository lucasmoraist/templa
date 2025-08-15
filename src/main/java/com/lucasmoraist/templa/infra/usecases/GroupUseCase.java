package com.lucasmoraist.templa.infra.usecases;

import com.lucasmoraist.templa.application.gateway.GroupGateway;
import com.lucasmoraist.templa.application.usecases.group.CreateGroupCase;
import com.lucasmoraist.templa.application.usecases.group.GetGroupByIdCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroupUseCase {

    @Bean
    public CreateGroupCase createGroupCase(GroupGateway groupGateway) {
        return new CreateGroupCase(groupGateway);
    }

    @Bean
    public GetGroupByIdCase getGroupByIdCase(GroupGateway groupGateway) {
        return new GetGroupByIdCase(groupGateway);
    }

}
