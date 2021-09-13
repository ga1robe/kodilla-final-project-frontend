package com.kodilla.servicefrontend.form;

import com.kodilla.servicefrontend.domain.HikingtrailsType;
import com.kodilla.servicefrontend.view.HikingtrailsView;
import com.kodilla.servicefrontend.domain.Hikingtrail;
import com.kodilla.servicefrontend.service.HikingtrailService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class HikingtrailsForm extends VerticalLayout {
    private TextField name = new TextField("Name");
    private TextField begin = new TextField("Begin");
    private TextField end = new TextField("End");
    private TextField totalDistance = new TextField("Total Distance");
    private ComboBox<HikingtrailsType> type = new ComboBox<>("Hikingtrail type");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private HikingtrailsView hikingtrailsView;
    private HikingtrailService service = HikingtrailService.getInstance();
    private Binder<Hikingtrail> binder = new Binder<Hikingtrail>(Hikingtrail.class);

    public HikingtrailsForm(HikingtrailsView hikingtrailsView) {
        type.setItems(HikingtrailsType.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        add(name, begin, end, totalDistance, type, buttons);
        this.hikingtrailsView = hikingtrailsView;
    }

    private void save() {
        Hikingtrail hikingtrail = binder.getBean();
        service.save(hikingtrail);
        hikingtrailsView.refresh();
        setHikingTrail(null);
    }

    private void delete() {
        Hikingtrail hikingtrail = binder.getBean();
        service.delete(hikingtrail);
        hikingtrailsView.refresh();
        setHikingTrail(null);
    }

    public void setHikingTrail(Hikingtrail hikingtrail) {
        binder.setBean(hikingtrail);
        if (hikingtrail == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}
