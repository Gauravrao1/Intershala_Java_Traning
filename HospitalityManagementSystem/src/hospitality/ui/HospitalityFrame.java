package hospitality.ui;

import hospitality.model.Guest;
import hospitality.model.Hotel;
import hospitality.model.Reservation;
import hospitality.model.Room;
import hospitality.service.HospitalityService;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class HospitalityFrame extends JFrame {
        private final HospitalityService service;
        private final JTabbedPane tabs = new JTabbedPane();

        public HospitalityFrame(HospitalityService service) {
                this.service = service;
                setTitle("Hospitality Management System");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(900, 560);
                setLocationRelativeTo(null);
                add(tabs);
                refreshAll();
        }

        private void refreshAll() {
                tabs.removeAll();
                tabs.addTab("Hotels", createHotelsPanel());
                tabs.addTab("Rooms", createRoomsPanel());
                tabs.addTab("Guests", createGuestsPanel());
                tabs.addTab("Reservations", createReservationsPanel());
        }

        private JPanel createHotelsPanel() {
                JTable table = table("ID", "Name", "Location", "Amenities");
                for (Hotel hotel : service.hotels())
                        addRow(table, hotel.getId(), hotel.getName(), hotel.getLocation(), hotel.getAmenities());
                JPanel panel = panelWithTable(table);
                JButton add = new JButton("Add Hotel");
                add.addActionListener(event -> {
                        JTextField name = new JTextField();
                        JTextField location = new JTextField();
                        JTextField amenities = new JTextField();
                        if (form("Add Hotel", new String[] { "Name", "Location", "Amenities" },
                                        new JTextField[] { name, location, amenities })) {
                                service.addHotel(name.getText(), location.getText(), amenities.getText());
                                refreshAll();
                        }
                });
                JButton delete = deleteButton(table, "hotel");
                panel.add(buttonBar(add, delete), BorderLayout.SOUTH);
                return panel;
        }

        private JPanel createRoomsPanel() {
                JTable table = table("ID", "Hotel", "Room", "Type", "Price", "Status");
                for (Room room : service.rooms())
                        addRow(table, room.getId(), service.hotelName(room.getHotelId()), room.getRoomNumber(),
                                        room.getType(), room.getPrice(), room.getStatus());
                JPanel panel = panelWithTable(table);
                JButton add = new JButton("Add Room");
                add.addActionListener(event -> {
                        JComboBox<Hotel> hotel = new JComboBox<>(service.hotels().toArray(new Hotel[0]));
                        JTextField number = new JTextField();
                        JTextField type = new JTextField();
                        JTextField price = new JTextField();
                        if (form("Add Room", new String[] { "Hotel", "Room number", "Type", "Price" },
                                        new java.awt.Component[] { hotel, number, type, price })) {
                                try {
                                        service.addRoom(((Hotel) hotel.getSelectedItem()).getId(), number.getText(),
                                                        type.getText(), Double.parseDouble(price.getText()));
                                        refreshAll();
                                } catch (NumberFormatException ex) {
                                        error("Price must be a number.");
                                }
                        }
                });
                JButton delete = deleteButton(table, "room");
                panel.add(buttonBar(add, delete), BorderLayout.SOUTH);
                return panel;
        }

        private JPanel createGuestsPanel() {
                JTable table = table("ID", "Name", "Email", "Phone");
                for (Guest guest : service.guests())
                        addRow(table, guest.getId(), guest.getName(), guest.getEmail(), guest.getPhone());
                JPanel panel = panelWithTable(table);
                JButton add = new JButton("Add Guest");
                add.addActionListener(event -> {
                        JTextField name = new JTextField();
                        JTextField email = new JTextField();
                        JTextField phone = new JTextField();
                        if (form("Add Guest", new String[] { "Name", "Email", "Phone" },
                                        new JTextField[] { name, email, phone })) {
                                service.addGuest(name.getText(), email.getText(), phone.getText());
                                refreshAll();
                        }
                });
                JButton delete = deleteButton(table, "guest");
                panel.add(buttonBar(add, delete), BorderLayout.SOUTH);
                return panel;
        }

        private JPanel createReservationsPanel() {
                JTable table = table("ID", "Room", "Guest", "Check-in", "Check-out", "Nights", "Cost");
                for (Reservation reservation : service.reservations())
                        addRow(table, reservation.getId(), reservation.getRoomId(),
                                        service.guestName(reservation.getGuestId()), reservation.getCheckIn(),
                                        reservation.getCheckOut(), reservation.getNights(),
                                        service.calculateCost(reservation));
                JPanel panel = panelWithTable(table);
                JButton add = new JButton("Make Reservation");
                add.addActionListener(event -> addReservation());
                JButton delete = deleteButton(table, "reservation");
                panel.add(buttonBar(add, delete), BorderLayout.SOUTH);
                return panel;
        }

        private void addReservation() {
                JComboBox<Room> room = new JComboBox<>(service.rooms().toArray(new Room[0]));
                JComboBox<Guest> guest = new JComboBox<>(service.guests().toArray(new Guest[0]));
                JTextField checkIn = new JTextField(LocalDate.now().toString());
                JTextField checkOut = new JTextField(LocalDate.now().plusDays(1).toString());
                if (!form("Make Reservation",
                                new String[] { "Room", "Guest", "Check-in (yyyy-mm-dd)", "Check-out (yyyy-mm-dd)" },
                                new java.awt.Component[] { room, guest, checkIn, checkOut }))
                        return;
                try {
                        service.reserve(((Room) room.getSelectedItem()).getId(),
                                        ((Guest) guest.getSelectedItem()).getId(), LocalDate.parse(checkIn.getText()),
                                        LocalDate.parse(checkOut.getText()));
                        refreshAll();
                } catch (RuntimeException ex) {
                        error(ex.getMessage());
                }
        }

        private JButton deleteButton(JTable table, String type) {
                JButton delete = new JButton("Delete Selected");
                delete.addActionListener(event -> {
                        int row = table.getSelectedRow();
                        if (row < 0) {
                                error("Select a row first.");
                                return;
                        }
                        int id = (int) table.getValueAt(row, 0);
                        switch (type) {
                                case "hotel" -> service.deleteHotel(id);
                                case "room" -> service.deleteRoom(id);
                                case "guest" -> service.deleteGuest(id);
                                case "reservation" -> service.deleteReservation(id);
                        }
                        refreshAll();
                });
                return delete;
        }

        private static JTable table(String... columns) {
                return new JTable(new DefaultTableModel(columns, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                });
        }

        private static void addRow(JTable table, Object... values) {
                ((DefaultTableModel) table.getModel()).addRow(values);
        }

        private static JPanel panelWithTable(JTable table) {
                JPanel panel = new JPanel(new BorderLayout(8, 8));
                panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panel.add(new JScrollPane(table), BorderLayout.CENTER);
                return panel;
        }

        private static JPanel buttonBar(JButton... buttons) {
                JPanel panel = new JPanel();
                for (JButton button : buttons)
                        panel.add(button);
                return panel;
        }

        private static boolean form(String title, String[] labels, java.awt.Component[] inputs) {
                JPanel panel = new JPanel(new GridLayout(labels.length, 2, 8, 8));
                for (int i = 0; i < labels.length; i++) {
                        panel.add(new JLabel(labels[i]));
                        panel.add(inputs[i]);
                }
                return JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION;
        }

        private static void error(String message) {
                JOptionPane.showMessageDialog(null, message, "Validation error", JOptionPane.ERROR_MESSAGE);
        }
}
