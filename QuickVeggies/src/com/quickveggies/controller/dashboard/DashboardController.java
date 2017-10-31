package com.quickveggies.controller.dashboard;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import com.quickveggies.CosmeticStyles;
import com.quickveggies.GeneralMethods;
import com.quickveggies.Main;
import com.quickveggies.controller.AbstractFreshEntryController;
import com.quickveggies.controller.AuditLogController;
import com.quickveggies.controller.FreshEntryController;
import com.quickveggies.controller.MoneyPaidRecdController;
import com.quickveggies.controller.MoneyPaidRecdController.AmountType;
import com.quickveggies.controller.SessionDataController;
import com.quickveggies.controller.SupplierCreditController;
import com.quickveggies.db.DatabaseClient;
import com.quickveggies.entities.User;
import com.quickveggies.entities.PartyType;
import com.quickveggies.misc.SearchPartyButton;
import com.quickveggies.model.EntityType;
import fy.Provider;

public class DashboardController extends AbstractFreshEntryController implements Initializable {

    public static final String STAGE_CLOSED_MANUALLY = "closedManually";

    @FXML
    private SplitPane splitView;
    
    @FXML
    private AnchorPane rightView;
    
    @FXML
    private AnchorPane toolbarView;

    @FXML
    private Pane transSubmenu;

    @FXML
    private Button dashboard;

    @FXML
    private Button buyer;

    @FXML
    private Button advancePay;

    @FXML
    private Button supplierCredit;

    @FXML
    private Button suppliers;

    @FXML
    private Button ladaan;

    @FXML
    private Button transactions;

    @FXML
    private Label loggedAs;

    @FXML
    private Button sales;

    @FXML
    private Button banking;

    @FXML
    private Button expenses;

    @FXML
    private Button expensesAdd;

    @FXML
    private Button teeps;

    @FXML
    private Button closePlusMenu;

    @FXML
    private Button reports;

    @FXML
    private Button freshEntry;

    @FXML
    private Button newGodown;

    @FXML
    private Button newColdstore;
    @FXML
    private Button coldstore;
    @FXML
    private Button godown;

    @FXML
    private Button accounts;

    @FXML
    private AnchorPane mainView;

    @FXML
    private Button createEntry;

    @FXML
    private Button btnAuditLog;

    @FXML
    private SearchPartyButton searchProfile;

    @FXML
    private Pane entryMenu;

    @FXML
    private AnchorPane leftAnchor;

    @FXML
    private Button settings;

    @FXML
    private Label lblPendingLadaan;

    private SessionDataController sessionController;
    
    private Node hold;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Provider.GUIManager.initialize(Main.getStage(), splitView);
        CosmeticStyles.addHoverEffect(dashboard, buyer, suppliers, ladaan, transactions, reports, accounts);
        CosmeticStyles.addHoverEffect(banking, sales, godown, coldstore, expenses, teeps);
        // CosmeticStyles.addHoverEffect(freshEntry,expensesAdd);
        //Todo: Disable unused button
        banking.setVisible(false);
        transSubmenu.setMaxHeight(transSubmenu.getPrefHeight() - 26);

        DatabaseClient dbclient = DatabaseClient.getInstance();
        if (dbclient == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "See log for more information. Exiting...",
                    ButtonType.CLOSE);
            alert.setHeaderText("Can't connect to the database!");
            alert.setResizable(true);
            alert.showAndWait();
            throw new IllegalAccessError("Can't connect to the database. Exiting...");
        }
        sessionController = SessionDataController.getInstance();
        try {
            sessionController.setColdstore(dbclient.countSpecificRows("arrival", "type", "coldstore"));
            sessionController.setGodown(dbclient.countSpecificRows("arrival", "type", "godown"));
        } catch (SQLException e) {
            System.out.println("sqlexception in counting coldstore");
        }

        System.out.println("coldstore=" + sessionController.getColdstore());
        System.out.println("godown=" + sessionController.getGodown());

        User user = SessionDataController.getInstance().getCurrentUser();
        if (user != null) {
            loggedAs.setText(user.getName());
        }
        
        suppliers.setOnAction((ActionEvent event) -> {
            try {
                mainView.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/supplierdash.fxml")));
                setupDashboardAnchors();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnAuditLog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPopup("/auditlogviewer.fxml", "Audit Log", new AuditLogController(DashboardController.this));
            }
        });

        settings.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    mainView.getChildren()
                            .setAll((Node) FXMLLoader.load(getClass()
                                    .getResource("/settings_dash.fxml")));
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        dashboard.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/introdash.fxml"));
                    loader.setController(new IntroDashController(DashboardController.this));
                    mainView.getChildren().setAll((Node) loader.load());
                    setupDashboardAnchors();
                    /*					Label dashDate = (Label) ((Pane) mainView.getChildren().get(0)).getChildren().get(2);
					LocalDateTime date = LocalDateTime.now();
					dashDate.setText("" + date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ", "
							+ date.getDayOfMonth() + ", "
							+ date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ", " + date.getYear());
                     */ /*
					 * for (Node node : ((Pane)
					 * mainView.getChildren().get(0)).getChildren()) {
					 * System.out.print(((Pane)
					 * mainView.getChildren().get(0)).getChildren().indexOf(
					 * node) + " - "); System.out.println(node.getId()); }
                     */
 /*
					 * SessionDataController session =
					 * SessionDataController.getCurrentUser(); lblPendingLadaan =
					 * (Label) ((Pane)
					 * mainView.getChildren().get(0)).getChildren().get(5);
					 * lblPendingLadaan.textProperty().bindBidirectional(session
					 * .pendingLadaanEntriesProp);
					 * lblPendingLadaan.textProperty().addListener(new
					 * ChangeListener<String>() {
					 * 
					 * @Override public void changed(ObservableValue<? extends
					 * String> observable, String oldValue, String newValue) {
					 * int pendingLads = Integer.valueOf(newValue); if
					 * (pendingLads == 0) {
					 * lblPendingLadaan.setTextFill(Color.GREEN); } else if
					 * (pendingLads > 0) {
					 * lblPendingLadaan.setTextFill(Color.HOTPINK); } } });
					 * lblPendingLadaan.setOnMouseClicked(new
					 * EventHandler<MouseEvent>() {
					 * 
					 * @Override public void handle(MouseEvent event) { if
					 * (event.getButton() != null) { if (event.getButton() ==
					 * MouseButton.PRIMARY) { ladaan.fire(); } } } });
					 * lblPendingLadaan.setTooltip(new
					 * Tooltip("Click to open Ladaan/Bijak Dashboard"));
					 * lblPendingLadaan.setBackground( new Background(new
					 * BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY,
					 * Insets.EMPTY)));
					 * addHoverEffectsToControl(lblPendingLadaan);
					 * 
					 * session.resetPendingLadaanEntries();
                     */
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        dashboard.fire();// display the main dash at the beginning of program

        buyer.setOnAction((ActionEvent event) -> {
            try {
                DBuyerController controller = new DBuyerController(DBuyerController.REGULAR);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/buyerdash.fxml"));
                loader.setController(controller);
                mainView.getChildren().setAll((Node) loader.load());
                setupDashboardAnchors();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        ladaan.setOnAction((ActionEvent event) -> {
            try {
                DBuyerController controller = new DBuyerController(DBuyerController.LADAAN_BIJAK);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/buyerdash.fxml"));
                loader.setController(controller);
                mainView.getChildren().setAll((Node) loader.load());
                setupDashboardAnchors();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        teeps.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    DTeepController controller = new DTeepController();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/teepdash.fxml"));
                    loader.setController(controller);
//                    mainView.getChildren().setAll((Node) loader.load());
                    Provider.GUIManager.replaceMainPane((Node) loader.load());
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        freshEntry.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                final Stage stage = new Stage();
                stage.centerOnScreen();
                stage.setTitle("Fresh Single Entry System");
                stage.initModality(Modality.APPLICATION_MODAL);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/freshentry.fxml"));
                    final FreshEntryController controller = new FreshEntryController(FreshEntryController.REGULAR);
                    loader.setController(controller);
                    Parent parent = loader.load();
                    Scene scene = new Scene(parent);
                    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.ESCAPE) {
                                stage.close();
                            }
                            controller.keyPressed(event.getCode());
                        }
                    });
                    // set the focus traverse logic
                    // ----------------------------
                    java.util.ArrayList<javafx.scene.control.TextField> txtFields = GeneralMethods
                            .getAllTxtFields(parent);
                    // -----------------
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent event) {
                            sessionController.setUnsavedWindows(sessionController.getUnsavedWindows() - 1);
                        }
                    });
                    sessionController.setUnsavedWindows(sessionController.getUnsavedWindows() + 1);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        newGodown.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    closePlusMenu.fire();
                    mainView.getChildren().clear();
                    DPendingSalesController controller = new DPendingSalesController("GODOWN");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/godowndash.fxml"));
                    loader.setController(controller);
                    mainView.getChildren().setAll((Node) loader.load());
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        newColdstore.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    closePlusMenu.fire();
                    mainView.getChildren().clear();
                    DPendingSalesController controller = new DPendingSalesController("COLDSTORE");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/godowndash.fxml"));
                    loader.setController(controller);
                    mainView.getChildren().setAll((Node) loader.load());
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        searchProfile.setPartyType(PartyType.BUYER_SUPPLIERS);

        createEntry.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    entryMenu.setVisible(false);
                    splitView.setEffect(null);
                }
            }
        });
        createEntry.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                switchVisible(entryMenu, splitView);
            }
        });
        transactions.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                double leyoutY;
                if (!transSubmenu.isVisible()) {
                    leyoutY = transSubmenu.getLayoutY() + transSubmenu.getHeight() + 1;
                    accounts.setLayoutY(leyoutY);
                    reports.setLayoutY(leyoutY + accounts.getHeight());
                    transSubmenu.setVisible(true);
                } else {
                    leyoutY = transactions.getLayoutY() + transactions.getHeight() + 1;
                    reports.setLayoutY(leyoutY);
                    accounts.setLayoutY(leyoutY + reports.getHeight());
                    transSubmenu.setVisible(false);
                }
            }

        });
        sales.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    mainView.getChildren().clear();
                    DSalesTransController controller = new DSalesTransController(FreshEntryController.REGULAR);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/salesdash.fxml"));
                    loader.setController(controller);
                    Provider.GUIManager.replaceMainPane((Node) loader.load());
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        accounts.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    mainView.getChildren().clear();
                    DBankAccountsController controller = new DBankAccountsController(accounts);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/bankdash.fxml"));
                    loader.setController(controller);
                    mainView.getChildren().setAll((Node) loader.load());
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        godown.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    mainView.getChildren().clear();
                    DPendingSalesController controller = new DPendingSalesController("GODOWN");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/godowndash.fxml"));
                    loader.setController(controller);
                    mainView.getChildren().setAll((Node) loader.load());
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        coldstore.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    mainView.getChildren().clear();
                    DPendingSalesController controller = new DPendingSalesController("COLDSTORE");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/godowndash.fxml"));
                    loader.setController(controller);
                    mainView.getChildren().setAll((Node) loader.load());
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        banking.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    mainView.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/bankdash.fxml")));
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        expenses.setText("Expenditure");
        expenses.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    hold = ((Node) FXMLLoader.load(getClass().getResource("/expendituredash.fxml")));
                    Provider.GUIManager.replaceMainPane(hold);
//                    mainView.getChildren()
//                            .setAll(hold);
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // initialize(location, resources, freshEntry);
        expensesAdd.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    mainView.getChildren()
                            .setAll((Node) FXMLLoader.load(getClass().getResource("/expendituredash.fxml")));
                    switchVisible(entryMenu, splitView);
                    setupDashboardAnchors();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        closePlusMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                switchVisible(entryMenu, splitView);
            }
        });
        advancePay.setOnAction(new EventHandler<ActionEvent>() {
            EntityType partyType = EntityType.SUPPLIER;
            AmountType amountType = null;

            @Override
            public void handle(ActionEvent event) {
                String title = "Advance Payment";
                amountType = AmountType.PAID;
                if (amountType != null) {
                    MoneyPaidRecdController controller = new MoneyPaidRecdController(partyType, amountType, true);
                    showPopup("/moneypaid.fxml", title, controller);
                }
            }
        });
        supplierCredit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                SupplierCreditController controller = new SupplierCreditController();
                showPopup("/suppliercredits.fxml", "Supplier Credit List", controller);
            }
        });
    }
    
    private void setupDashboardAnchors() {
        if (mainView.getChildren().get(0) != null) {
            AnchorPane.setTopAnchor(mainView.getChildren().get(0), 1.0);
            AnchorPane.setLeftAnchor(mainView.getChildren().get(0), 1.0);
            AnchorPane.setBottomAnchor(mainView.getChildren().get(0), 1.0);
            AnchorPane.setRightAnchor(mainView.getChildren().get(0), 1.0);
        }
    }

    public static Stage showPopup(String resource, String title, Object controller) {
        final Stage stage = new Stage();
        stage.getProperties().put(STAGE_CLOSED_MANUALLY, Boolean.FALSE);
        try {
            stage.initStyle(StageStyle.UTILITY);
            stage.centerOnScreen();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent event) {
                    stage.getProperties().put(STAGE_CLOSED_MANUALLY, Boolean.TRUE);
                    Main.getStage().getScene().getRoot().setEffect(null);
                }
            });
            try {
                FXMLLoader loader = new FXMLLoader(DashboardController.class.getResource(resource));
                loader.setController(controller);
                Scene scene = new Scene((Parent) loader.load());

                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.ESCAPE) {
                            Main.getStage().getScene().getRoot().setEffect(null);
                            stage.getProperties().put(STAGE_CLOSED_MANUALLY, Boolean.TRUE);
                            stage.close();
                        }
                    }
                });

                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stage;

    }

    public void switchVisible(Node node, Node nodeTwo) {
        if (!node.isVisible()) {
            node.setVisible(true);
//			nodeTwo.setEffect(new MotionBlur());
        } else {
            node.setVisible(false);
//			nodeTwo.setEffect(null);
        }
    }

    public void fireLadaan() {
        ladaan.fire();
    }

    public void fireGodown() {
        godown.fire();
    }

    public void fireColdstore() {
        coldstore.fire();
    }

    public void fireBuyerDeals() {
        buyer.fire();
    }

    public void fireSupplierDeals() {
        suppliers.fire();
    }

    public void fireArrival() {
        sales.fire();
    }

    public void fireBanking() {
        banking.fire();
    }
}
