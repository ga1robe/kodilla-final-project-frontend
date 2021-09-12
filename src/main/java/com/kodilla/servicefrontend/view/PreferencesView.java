package com.kodilla.servicefrontend.view;

import com.kodilla.servicefrontend.backend.api.PreferenceFacade;
import com.kodilla.servicefrontend.domain.Preference;
import com.kodilla.servicefrontend.form.PreferencesForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@UIScope
@Route()
public class PreferencesView extends VerticalLayout {
    private final PreferenceFacade preferencesFacade;
    private PreferencesForm preferencesForm;
    private Grid<Preference> grid;
    private Button addNewPreference;

    TextField filterField = new TextField();

    public void refresh(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(preferencesFacade.getAllPreferences());
        }
        else if ( filterText.chars().allMatch(Character::isDigit) ) {
            grid.setItems(preferencesFacade.getPreferenceById( Long.parseLong( filterText ) ));
        } else {
            grid.setItems(preferencesFacade.getPreferencesByDestinationPoint( filterText ));
        }
    }

    public PreferencesView(PreferenceFacade preferencesFacade) {
        this.preferencesFacade = preferencesFacade;
        this.preferencesForm = new PreferencesForm(this.preferencesFacade, this);
        this.grid = new Grid<>(Preference.class);
        this.addNewPreference = new Button("Add new Preference");

        addNewPreference.addClickListener(e -> {
            grid.asSingleSelect().clear();
            preferencesForm.setPreference(new Preference());
        });

        preferencesForm.setPreference(null);

        filterField.setPlaceholder("Filder by ID or departure point name");
        filterField.setClearButtonVisible(true);
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.addValueChangeListener(e -> refresh(e.getValue()));

        grid.setColumns("id", "userId", "trailBegin", "trailEnd", "minTemperature", "distance");
//        grid.setColumns("id", "userDto", "trailBegin", "trailEnd", "minTemperature", "distance");
        HorizontalLayout toolbar = new HorizontalLayout(filterField, addNewPreference);
        HorizontalLayout mainContent = new HorizontalLayout(grid, preferencesForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh("");

        grid.asSingleSelect().addValueChangeListener(event -> preferencesForm.setPreference(grid.asSingleSelect().getValue()));

    }
}
