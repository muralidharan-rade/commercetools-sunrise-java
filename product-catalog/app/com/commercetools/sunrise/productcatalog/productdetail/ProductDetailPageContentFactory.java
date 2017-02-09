package com.commercetools.sunrise.productcatalog.productdetail;

import com.commercetools.sunrise.common.models.PageContentFactory;
import com.commercetools.sunrise.common.reverserouter.CartReverseRouter;
import com.commercetools.sunrise.common.utils.PageTitleResolver;
import com.commercetools.sunrise.productcatalog.common.ProductBeanFactory;

import javax.inject.Inject;
import java.util.Locale;

import static java.util.Collections.singletonList;

public class ProductDetailPageContentFactory extends PageContentFactory<ProductDetailPageContent, ProductDetailControllerData> {

    private final Locale locale;
    private final PageTitleResolver pageTitleResolver;
    private final CartReverseRouter cartReverseRouter;
    private final ProductBreadcrumbBeanFactory productBreadcrumbBeanFactory;
    private final ProductBeanFactory productBeanFactory;

    @Inject
    public ProductDetailPageContentFactory(final Locale locale, final PageTitleResolver pageTitleResolver, final CartReverseRouter cartReverseRouter,
                                           final ProductBreadcrumbBeanFactory productBreadcrumbBeanFactory, final ProductBeanFactory productBeanFactory) {
        this.locale = locale;
        this.pageTitleResolver = pageTitleResolver;
        this.cartReverseRouter = cartReverseRouter;
        this.productBreadcrumbBeanFactory = productBreadcrumbBeanFactory;
        this.productBeanFactory = productBeanFactory;
    }

    @Override
    protected ProductDetailPageContent getViewModelInstance() {
        return new ProductDetailPageContent();
    }

    @Override
    public final ProductDetailPageContent create(final ProductDetailControllerData data) {
        return super.create(data);
    }

    @Override
    protected final void initialize(final ProductDetailPageContent model, final ProductDetailControllerData data) {
        super.initialize(model, data);
        fillProduct(model, data);
        fillBreadCrumb(model, data);
        fillAddToCartFormUrl(model, data);
    }

    @Override
    protected void fillTitle(final ProductDetailPageContent model, final ProductDetailControllerData data) {
        final String title = String.format("%s %s",
                data.getProductWithVariant().getProduct().getName().find(singletonList(locale)).orElse(""),
                pageTitleResolver.getOrEmpty("catalog:productDetailPage.title"));
        model.setTitle(title);
    }

    protected void fillAddToCartFormUrl(final ProductDetailPageContent content, final ProductDetailControllerData data) {
        content.setAddToCartFormUrl(cartReverseRouter.processAddProductToCartForm(locale.toLanguageTag()).url()); // TODO move to page meta
    }

    protected void fillBreadCrumb(final ProductDetailPageContent content, final ProductDetailControllerData data) {
        content.setBreadcrumb(productBreadcrumbBeanFactory.create(data));
    }

    protected void fillProduct(final ProductDetailPageContent content, final ProductDetailControllerData data) {
        content.setProduct(productBeanFactory.create(data.getProductWithVariant()));
    }
}
