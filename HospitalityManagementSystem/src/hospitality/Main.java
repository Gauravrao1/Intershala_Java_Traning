package hospitality;

import hospitality.service.HospitalityService;
import hospitality.ui.HospitalityFrame;
import javax.swing.SwingUtilities;

public class Main {
        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new HospitalityFrame(new HospitalityService()).setVisible(true));
        }
}
