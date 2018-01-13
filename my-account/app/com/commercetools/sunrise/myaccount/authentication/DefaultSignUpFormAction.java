package com.commercetools.sunrise.myaccount.authentication;

import com.commercetools.sunrise.core.controllers.AbstractFormAction;
import com.commercetools.sunrise.models.customers.CustomerCreator;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static com.commercetools.sdk.errors.ErrorResponseExceptionUtils.isDuplicatedEmailFieldError;

public class DefaultSignUpFormAction extends AbstractFormAction<SignUpFormData> implements SignUpFormAction {

    private final SignUpFormData formData;
    private final CustomerCreator customerCreator;

    @Inject
    protected DefaultSignUpFormAction(final FormFactory formFactory, final SignUpFormData formData,
                                      final CustomerCreator customerCreator) {
        super(formFactory);
        this.formData = formData;
        this.customerCreator = customerCreator;
    }

    @Override
    protected Class<? extends SignUpFormData> formClass() {
        return formData.getClass();
    }

    @Override
    protected CompletionStage<?> onValidForm(final SignUpFormData formData) {
        return customerCreator.get(formData.customerDraft());
    }

    @Override
    protected CompletionStage<Result> onFailedRequest(final Form<? extends SignUpFormData> form, final Throwable throwable,
                                                      final Function<Form<? extends SignUpFormData>, CompletionStage<Result>> onBadRequest) {
        if (isDuplicatedEmailFieldError(throwable.getCause())) {
            form.reject("errors.emailAlreadyExists");
            return onBadRequest.apply(form);
        }
        return super.onFailedRequest(form, throwable, onBadRequest);
    }
}