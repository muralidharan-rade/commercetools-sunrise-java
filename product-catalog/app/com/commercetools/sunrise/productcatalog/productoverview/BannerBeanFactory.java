package com.commercetools.sunrise.productcatalog.productoverview;

import com.commercetools.sunrise.common.contexts.RequestScoped;
import com.commercetools.sunrise.common.models.ViewModelFactory;
import com.commercetools.sunrise.common.utils.LocalizedStringResolver;

import javax.inject.Inject;
import java.util.Optional;

@RequestScoped
public class BannerBeanFactory extends ViewModelFactory<BannerBean, ProductOverviewControllerData> {

    private final LocalizedStringResolver localizedStringResolver;

    @Inject
    public BannerBeanFactory(final LocalizedStringResolver localizedStringResolver) {
        this.localizedStringResolver = localizedStringResolver;
    }

    @Override
    protected BannerBean getViewModelInstance() {
        return new BannerBean();
    }

    @Override
    public final BannerBean create(final ProductOverviewControllerData data) {
        return super.create(data);
    }

    @Override
    protected final void initialize(final BannerBean model, final ProductOverviewControllerData data) {
        fillTitle(model, data);
        fillDescription(model, data);
    }

    protected void fillDescription(final BannerBean bean, final ProductOverviewControllerData data) {
        if (data.getCategory() != null) {
            Optional.ofNullable(data.getCategory().getDescription())
                    .ifPresent(description -> bean.setDescription(localizedStringResolver.getOrEmpty(description)));
        }
    }

    protected void fillTitle(final BannerBean bean, final ProductOverviewControllerData data) {
        if (data.getCategory() != null) {
            bean.setTitle(localizedStringResolver.getOrEmpty(data.getCategory().getName()));
        }
    }
}