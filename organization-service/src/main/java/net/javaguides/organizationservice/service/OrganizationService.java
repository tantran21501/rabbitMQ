package net.javaguides.organizationservice.service;

import net.javaguides.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organization);

    OrganizationDto getOrganizationByCode(String organizationCode);
}
