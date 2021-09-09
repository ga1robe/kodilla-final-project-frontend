package com.kodilla.servicefrontend.form;

import com.kodilla.servicefrontend.backend.api.PreferenceFacade;
import com.kodilla.servicefrontend.domain.Preference;
import com.kodilla.servicefrontend.view.PreferencesView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class PreferencesForm extends FormLayout {
    private final PreferenceFacade preferencesFacade;
    private PreferencesView preferencesView;

    private TextField userId = new TextField("userId");
    private TextField departureCity = new TextField("departureCity");
    private TextField destinationCity = new TextField("destinationCity");
    private TextField minTemperature = new TextField("minTemperature");
    private TextField maxPrice = new TextField("maxPrice");
    private Binder<Preference> binder = new Binder<>(Preference.class);

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button delete = new Button("Delete", VaadinIcon.CHECK.create());

    @Autowired
    public PreferencesForm(PreferenceFacade facade, PreferencesView view) {
        this.preferencesFacade = facade;
        this.preferencesView = view;
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(userId, departureCity,destinationCity, minTemperature, maxPrice, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setPreference(Preference preference) {
        binder.setBean(preference);

        if (preference == null) {
            setVisible(false);
        } else {
            setVisible(true);
            userId.focus();
        }
    }

    void save() {
        Preference preference = binder.getBean();

        if( preference.isSafeToSave() && preference.getId().equals("") ) {
            preferencesFacade.addPreference(preference);
        } else if(preference.isSafeToUpdate()) {
            preferencesFacade.updatePreference(preference);
        } else {
            Notification.show("Fields are not filled properly!");
        }

        preferencesView.refresh("");
        setPreference(null);
    }

    void delete() {
        Preference preference = binder.getBean();

        if(preference.getId().chars().allMatch(Character::isDigit)) {
            preferencesFacade.deletePreference( Long.parseLong( preference.getId() ) );
        }
        preferencesView.refresh("");
        setPreference(null);
    }
}
