package com.denglitong.organizationservice.service;

import com.denglitong.organizationservice.model.Organization;
import com.denglitong.organizationservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/24
 */
@Service
public class OrganizationService {

    private OrganizationRepository orgRepository;

    @Autowired
    public void setOrgRepository(OrganizationRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    public Organization getOrg(String organizationId) {
        Organization org = orgRepository.findByOrganizationId(organizationId);
        org.setContactName("OLD::" + org.getContactName());
        return org;
    }

    public void saveOrg(Organization org) {
        org.setOrganizationId(UUID.randomUUID().toString());
        orgRepository.save(org);
    }

    public void updateOrg(Organization org) {
        orgRepository.save(org);
    }

    public void deleteOrg(String organizationId) {
        orgRepository.deleteById(organizationId);
    }
}
