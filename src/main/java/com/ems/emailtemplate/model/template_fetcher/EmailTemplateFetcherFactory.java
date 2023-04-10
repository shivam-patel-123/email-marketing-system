package com.ems.emailtemplate.model.template_fetcher;

import com.ems.emailtemplate.persistent.ITemplatePersistent;

public class EmailTemplateFetcherFactory implements ITemplateFetcherFactory {
    @Override
    public ITemplateFetcher createTemplateFetcher(ITemplatePersistent templatePersistent) {
        return new EmailTemplateFetcher(templatePersistent);
    }
}
