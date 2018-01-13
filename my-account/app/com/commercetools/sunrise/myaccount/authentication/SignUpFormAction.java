package com.commercetools.sunrise.myaccount.authentication;

import com.commercetools.sunrise.core.controllers.FormAction;
import com.google.inject.ImplementedBy;

@ImplementedBy(DefaultSignUpFormAction.class)
@FunctionalInterface
public interface SignUpFormAction extends FormAction<SignUpFormData> {

}