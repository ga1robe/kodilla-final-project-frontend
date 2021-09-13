package com.kodilla.servicefrontend.view;

import com.kodilla.servicefrontend.form.HikingtrailsForm;
import com.kodilla.servicefrontend.domain.Hikingtrail;
import com.kodilla.servicefrontend.service.HikingtrailService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class HikingtrailsView extends VerticalLayout {

    private HikingtrailService hikingtrailService = HikingtrailService.getInstance();
    private Grid<Hikingtrail> grid = new Grid<>(Hikingtrail.class);
    private TextField filter = new TextField();
    private Binder<Hikingtrail> binder = new Binder<Hikingtrail>(Hikingtrail.class);
    private HikingtrailsForm form = new HikingtrailsForm(this);
    private Button addNewHikingTrail = new Button("Add new hiking trail");

    public HikingtrailsView() {
        filter.setPlaceholder("Filter by name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("name", "begin", "end", "totalDistance", "type");

        addNewHikingTrail.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setHikingTrail(new Hikingtrail());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewHikingTrail);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setHikingTrail(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setHikingTrail(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(hikingtrailService.getHikingtrails());
    }

    private void update() {
        grid.setItems(hikingtrailService.findByName(filter.getValue()));
    }


}