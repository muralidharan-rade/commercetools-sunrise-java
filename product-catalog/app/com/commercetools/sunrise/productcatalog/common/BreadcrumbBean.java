package com.commercetools.sunrise.productcatalog.common;

import com.commercetools.sunrise.common.models.ViewModel;

import java.util.List;

public class BreadcrumbBean extends ViewModel {

    private List<BreadcrumbLinkBean> links;

    public BreadcrumbBean() {
    }

    public List<BreadcrumbLinkBean> getLinks() {
        return links;
    }

    public void setLinks(final List<BreadcrumbLinkBean> links) {
        this.links = links;
    }
}
