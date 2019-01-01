import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SierpinskiTriangle extends JApplet {

    // create two private member fields
    private JTextField jtfOrder = new JTextField("0",5);
    private SierpinskiTrianglePanel trianglePanel = new SierpinskiTrianglePanel();

    // Constructor
    public SierpinskiTriangle() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter an order: "));
        panel.add(jtfOrder);
        jtfOrder.setHorizontalAlignment(SwingConstants.RIGHT);

        add(trianglePanel);
        add(panel, BorderLayout.SOUTH);

        jtfOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trianglePanel.setOrder(Integer.parseInt(jtfOrder.getText()));
            }
        });
    }

    static class SierpinskiTrianglePanel extends JPanel {
        private int order = 0;

        public void setOrder(int order) {
            this.order = order;
            repaint();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Point p1 = new Point(getWidth()/2, 10);
            Point p2 = new Point(10, getHeight() - 10);
            Point p3 = new Point(getWidth() - 10, getHeight() - 10);

            displayTriangles(g, order, p1, p2, p3);
        }

        private static void displayTriangles(Graphics g, int order, Point p1, Point p2, Point p3) {

            // Draw outline of the triangle first
            if (order >= 0) {
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p1.x, p1.y, p3.x, p3.y);
                g.drawLine(p2.x, p2.y, p3.x, p3.y);

                //Get midpoints
                Point p12 = midpoint(p1, p2);
                Point p23 = midpoint(p2, p3);
                Point p13 = midpoint(p1, p3);

                // Recursive call to build triangles
                displayTriangles(g, order - 1, p1, p12, p13);
                displayTriangles(g, order - 1, p12, p2, p23);
                displayTriangles(g, order - 1, p13, p23, p3);

            }
        }

        //Create midpoint method
        private static Point midpoint(Point p1, Point p2) {
            return new Point((p1.x + p2.x)/2, (p1.y + p2.y)/2);
        }
    }

    // add main method to test
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Triangle");
        SierpinskiTriangle applet = new SierpinskiTriangle();
        frame.add(applet);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

}
