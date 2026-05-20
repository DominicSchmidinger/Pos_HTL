package com.example.application.views.personal;

import com.example.application.model.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Personal")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.PERSON_BOOTH_SOLID)
public class PersonalView extends VerticalLayout {
    private Personalbuero personalbuero;
    private Grid<Mitarbeiter> personalTabelle;
    private ComboBox<String> mitarbeiterTypFilter;
    private TextField searchNameField;
    private HorizontalLayout filterHorizontalLayout;
    private GridListDataView<Mitarbeiter> tableDataView;

    private HorizontalLayout fileManagementHorizontalLayout;
    private Button saveButton;

    private H3 fileManagementHeader;
    private H3 personalHeader;


    public PersonalView() {
        setSpacing(false);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);

        personalbuero = new Personalbuero();
        personalbuero.readPersonalFromCsv();

        initComponents();

        initListeners();

        add(fileManagementHeader,
                fileManagementHorizontalLayout,
                personalHeader,
                filterHorizontalLayout,
                personalTabelle);
    }

    private void initListeners() {
        personalTabelle.addItemClickListener(event -> {
            Notification.show(event.getItem().toString());
        });

        mitarbeiterTypFilter.addValueChangeListener(event -> {
            tableDataView.refreshAll();
           // Notification.show(event.getValue().toString() + " oder " + mitarbeiterTypFilter.getValue());
//            String typ = event.getValue();
//            if(typ == null || typ.equals("Alle")) {
//                personalTabelle.getListDataView().removeFilters();
//            }
//            else {
//                personalTabelle.getListDataView().setFilter(m -> {
//                    if(m.getClass().getSimpleName().equals(typ)) {
//                        return true;
//                    }
//                    return false;
//                });
//            }
        });

        searchNameField.addValueChangeListener(event -> {
            tableDataView.refreshAll();
            //Notification.show(event.getValue().toString());
        });

        saveButton.addClickListener(event -> {
            try {
                personalbuero.writePersonalToCsv();
            } catch (PersonalException e) {
                Notification.show(e.getMessage());
            }
        });
    }

    private void initComponents() {

        fileManagementHeader = new H3("Hinzufügen und Speichern");
        personalHeader = new H3("Personal");

        saveButton = new Button("Speichern");

        fileManagementHorizontalLayout = new HorizontalLayout();
        fileManagementHorizontalLayout.setWidthFull();
        fileManagementHorizontalLayout.add(saveButton);


        mitarbeiterTypFilter = new ComboBox<>("Mitarbeiter");
        mitarbeiterTypFilter.setItems("Alle", "Angestellter", "Arzt", "Freelancer");
        mitarbeiterTypFilter.setValue("Alle");

        searchNameField = new TextField();
        searchNameField.setWidth("40%");
        searchNameField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchNameField.setSuffixComponent(new Icon(VaadinIcon.FAMILY));
        searchNameField.setClearButtonVisible(true);
        searchNameField.setPlaceholder("Suche...");
        searchNameField.setValueChangeMode(ValueChangeMode.EAGER);

        filterHorizontalLayout = new HorizontalLayout();
        filterHorizontalLayout.setWidthFull();
        filterHorizontalLayout.setVerticalComponentAlignment(Alignment.END, searchNameField);
        filterHorizontalLayout.add(mitarbeiterTypFilter, searchNameField);

        initTable();
    }

    private void initTable() {
        personalTabelle = new Grid<>(Mitarbeiter.class, false);

        personalTabelle.addComponentColumn(m-> {
            Button deletButton = new Button(VaadinIcon.TRASH.create());
            deletButton.setThemeName("icon error");

            deletButton.addClickListener(event -> {
               Notification.show(m.getName());
               // tableDataView.removeItem(m); -> unmodifieable List kann nciht verändert werden
                try {
                    personalbuero.kuendigen(m.getName());
                    updateTable();
                } catch (PersonalException e) {
                    Notification.show("Exception: " + e.getMessage());
                }
            });

            return deletButton;
        }).setHeader("Löschen");

        personalTabelle.addColumn(m-> m.getClass().getSimpleName()).setHeader("Mitarbeiter");
        personalTabelle.addColumn(m -> m.getName()).setHeader("Name");
        personalTabelle.addColumn(m -> m.berechneAlter()).setHeader("Alter");
        personalTabelle.addColumn(m -> m.berechneDienstalter()).setHeader("D-Alter");

        // freelancer Spalten
        personalTabelle.addColumn(m -> {
            if(m instanceof Freelancer) {
                return ((Freelancer) m).getStunden();
            } else {
                return null;
            }
        }).setHeader("h");
        personalTabelle.addColumn(m -> {
            if(m instanceof Freelancer) {
                return ((Freelancer) m).getStundenSatz();
            } else {
                return null;
            }
        }).setHeader("h-Satz");

        // Arzt Attribute
        personalTabelle.addColumn(m -> m instanceof Arzt ? ((Arzt) m).getFixum(): null).setHeader("Fixum");
        personalTabelle.addColumn(m -> m instanceof Arzt ? ((Arzt) m).getWochenStunden(): null).setHeader("Wh");

        personalTabelle.getColumns().forEach(column -> column.setSortable(true).setAutoWidth(true));

        updateTable();
    }

    private void updateTable() {
        tableDataView = personalTabelle.setItems(personalbuero.getMitarbeiterListeCopy());
        tableDataView.setFilter(m -> filterTable(m));
    }

    private boolean filterTable(Mitarbeiter m) {
        String typFilter = mitarbeiterTypFilter.getValue();
        boolean typFilterResult = false;
        String nameFilter = searchNameField.getValue().trim();
        boolean nameFilterResult = false;

        if(typFilter == null ||
                typFilter.equals("Alle") ||
                m.getClass().getSimpleName().equals(typFilter)) {
            typFilterResult = true;
        }

        if(nameFilter == null ||
                nameFilter.isBlank() ||
                m.getName().toLowerCase().contains(nameFilter.toLowerCase())) {
            nameFilterResult = true;
        }
        return typFilterResult && nameFilterResult;
    }

}
