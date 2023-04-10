package com.ems.emailtemplate.model.template_fetcher;


import com.ems.emailtemplate.persistent.ITemplatePersistent;

public interface ITemplateFetcherFactory {
    ITemplateFetcher createTemplateFetcher(ITemplatePersistent templatePersistent);
}
