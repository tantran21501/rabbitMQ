package net.javaguides.organizationservice.mapper;

import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.entity.Organization;

public class OrganizationMapper {
    public static OrganizationDto getOrganizationDto(Organization organization) {
        return new OrganizationDto(organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreatedDate());
    }

    public static Organization getOrganization(OrganizationDto organizationDto) {
        return new Organization(organizationDto.getId(),
                organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationCode(),
                organizationDto.getCreatedDate());
    }
}
