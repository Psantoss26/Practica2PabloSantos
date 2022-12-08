
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formulario extends JFrame implements ActionListener, ItemListener, MouseListener, HierarchyListener {

    CardLayout ventanas;

    JButton boton1, boton2, boton3;

    JLabel labelTitulo;

    JLabel labelInicio, labelRegistro, labelNombre, labelCorreo, labelContraseña, labelResidencia, labelPaises, labelProvincias,
                labelEstados, labelDatos, verTerminos;
    JTextField textoNombre, textoCorreo, textoContraseña;

    JTextArea textoDatos;

    JLabel textoInicio;

    Boolean rutaPrueba;

    JCheckBox terminos, guardarDatos;

    JScrollPane scrollDatos;

    JButton botonGenerar, botonGuardar1, botonGuardar2;

    JLabel correcto1, correcto2, error1, error2;

    String datos, datos2, prov, est, p;

    JPanel panelVentanas, panelSur, panelNorte;

    JPanel ventana1, ventana2, ventana3, ventana4, ventana5;

    JComboBox paises, provincias, estados;

    String paisesArray[] = {"España", "EEUU"};

    String [] provinciasArray = new String [50];
    String [] estadosArray = new String [50];

    final static String VENTANA1 = "Ventana 1";
    final static String VENTANA2 = "Ventana 2";
    final static String VENTANA3 = "Ventana 3";
    final static String VENTANA4 = "Ventana 4";
    final static String VENTANA5 = "Ventana 5";

    Font fuente1 = new Font(Font.SANS_SERIF, Font.BOLD, 17);
    Font fuente2 = new Font(Font.SANS_SERIF, Font.PLAIN, 17);
    Font fuenteTitulo = new Font(Font.SANS_SERIF, Font.BOLD, 40);

    Border borde  = BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(134, 17, 17));

    public Formulario() {

        //Centro el JFrame en la pantalla
        setLocationRelativeTo(null);

        ventanas = new CardLayout();

        //Inicializo un border layout para el JFrame
        setLayout(new BorderLayout());

        //Panel principal Formulario
        panelVentanas = new JPanel();
        //panelVentanas.setBackground(new Color(70, 177, 214));
        add(panelVentanas);
        panelVentanas.setLayout(ventanas);


        //Panel lateral Izquierda con degradado
        PanelDegradado panel = new PanelDegradado();
        panel.setPreferredSize(new Dimension(150, 500));
        add(panel, BorderLayout.WEST);

        //PANEL NORTE
        panelNorte = new JPanel();
        labelTitulo = new JLabel();
        labelTitulo.setText("VENTANA DE REGISTRO");
        labelTitulo.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        panelNorte.setBackground(new Color(216, 156, 155));
        panelNorte.add(labelTitulo);
        Border borde1  = BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(134, 17, 17));
        panelNorte.setBorder(borde1);
        //Añado el panel a la parte superior del JFrame
        add(panelNorte, BorderLayout.NORTH);

        //Botón Continuar
        boton1 = new JButton("Continuar");
        boton1.addActionListener(this);
        boton1.setFont(fuente1);
        boton1.setBounds(400, 0, 150, 50);
        //botonImg.setSize(180, 50);
        boton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton1.setBorder(BorderFactory.createLineBorder(Color.black));
        boton1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        boton1.setFocusable(false);

        //Botón Volver
        boton2 = new JButton("Volver");
        boton2.addActionListener(this);
        boton2.setFont(fuente1);
        boton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton2.setBorder(BorderFactory.createLineBorder(Color.black));
        boton2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        boton2.setFocusable(false);
        boton2.setVisible(false);

        boton3 = new JButton("Finalizar");
        boton3.addActionListener(this);
        boton3.setFont(fuente1);
        boton3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton3.setBorder(BorderFactory.createLineBorder(Color.black));
        boton3.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        boton3.setFocusable(false);
        boton3.setVisible(false);

        //PANEL SUR
        panelSur = new JPanel();
        panelSur.setBackground(new Color(211, 35, 12));
        panelSur.add(boton2);
        panelSur.add(boton1);
        panelSur.add(boton3);
        panelSur.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
        Border borde2  = BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(134, 17, 17));
        panelSur.setBorder(borde2);
        //Añado el panel a la parte inferior del JFrame
        add(panelSur,BorderLayout.SOUTH);

        //Configurar y mostrar JFrame
        initPantalla();

        //Métodos
        ventana1();
        ventana2();
        ventana3();
        ventana4();
        ventana5();
        controladorVentanas();

    }

    public void initPantalla() {

        setTitle("Navegador BRAVE"); //Título del JFrame
        setBounds(400, 200, 1100,700); //Dimensiones del JFrame
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar proceso al cerrar ventana
        setVisible(true); //Mostrar JFrame
    }

    public void controladorVentanas() {

        panelVentanas.add(ventana1, VENTANA1);
        panelVentanas.add(ventana2, VENTANA2);
        panelVentanas.add(ventana3, VENTANA3);
        panelVentanas.add(ventana4, VENTANA4);
        panelVentanas.add(ventana5, VENTANA5);

        ventanas.show(panelVentanas, VENTANA1);
        SwingUtilities.updateComponentTreeUI(this);
        this.repaint();

        ventana1.addHierarchyListener(this);
        ventana2.addHierarchyListener(this);
        ventana3.addHierarchyListener(this);
        ventana4.addHierarchyListener(this);
        ventana5.addHierarchyListener(this);
    }

    public void ventana1() {

        ventana1 = new JPanel();
        ventana1.setLayout(null);
        ventana1.setBackground(new Color(216, 156, 155));
        //Borde personalizado
        ventana1.setBorder(borde);

        //JLabel Título --> ~ Bienvenido ~
        labelInicio = new JLabel("~ BIENVENIDO ~");
        labelInicio.setFont(fuenteTitulo);
        labelInicio.setBounds(0, 140, 920, 80);
        labelInicio.setHorizontalAlignment(SwingConstants.CENTER);
        ventana1.add(labelInicio);

        //JLabel texto con normas
        textoInicio = new JLabel("A continuación, deberá rellenar los campos con sus datos.");
        textoInicio.setFont(fuente1);
        textoInicio.setBounds(0, 230, 910, 50);
        textoInicio.setHorizontalAlignment(SwingConstants.CENTER);
        ventana1.add(textoInicio);

        //JCheckBox para aceptar términos
        terminos = new JCheckBox(" Aceptar términos y condiciones");
        terminos.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        terminos.setBounds(5, 280, 890, 50);
        terminos.setHorizontalAlignment(SwingConstants.CENTER);
        terminos.setBackground(null);
        ventana1.add(terminos);

        //JLabel ver términos y condiciones
        verTerminos = new JLabel("Ver términos y condiciones");
        verTerminos.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        //Subrayado
        Font font = verTerminos.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        verTerminos.setFont(font.deriveFont(attributes));

        verTerminos.setBounds(380, 320, 200, 40);
        verTerminos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        verTerminos.addMouseListener(this);
        ventana1.add(verTerminos);

    }

    public void ventana2() {

        ventana2 = new JPanel();
        ventana2.setLayout(null);
        ventana2.setBackground(new Color(216, 156, 155));
        //Borde personalizado
        ventana2.setBorder(borde);

        //JLabel Título --> ~ Registrar Usuario ~
        crearJLabel(labelRegistro, "~ NUEVO USUARIO ~", fuenteTitulo, 0, 60, 950, 80, true, ventana2);

        //JLabels Nombre, Correo y Contraseña
        labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(fuente1);
        labelNombre.setBounds(275, 170, 150, 40);
        ventana2.add(labelNombre);

        labelCorreo = new JLabel("Correo:");
        labelCorreo.setFont(fuente1);
        labelCorreo.setBounds(275, 240, 150, 40);
        ventana2.add(labelCorreo);

        labelContraseña = new JLabel("Contraseña:");
        labelContraseña.setFont(fuente1);
        labelContraseña.setBounds(275, 310, 150, 40);
        ventana2.add(labelContraseña);

        //JTextField
        textoNombre = new RoundJTextField(18);
        textoNombre.setFont(fuente2);
        textoNombre.setBounds(385, 170, 280, 40);
        ventana2.add(textoNombre);

        textoCorreo = new RoundJTextField(18);
        textoCorreo.setFont(fuente2);
        textoCorreo.setBounds(385, 240, 280, 40);
        ventana2.add(textoCorreo);

        textoContraseña = new RoundJTextField(18);
        textoContraseña.setFont(fuente2);
        textoContraseña.setBounds(385, 310, 280, 40);
        ventana2.add(textoContraseña);

        //Jbutton Guardar
        botonGuardar1 = new JButton("Guardar");
        botonGuardar1.setBounds(380, 400, 150, 40);
        botonGuardar1.setFont(fuente1);
        botonGuardar1.addActionListener(this);
        ventana2.add(botonGuardar1);

        //JLabel correcto
        correcto1 = new JLabel("Correcto");
        correcto1.setBounds(540, 400, 150, 40);
        correcto1.setFont(fuente1);
        correcto1.setForeground(Color.GREEN);
        correcto1.setVisible(false);
        ventana2.add(correcto1);

        //JLabel ERROR
        error1 = new JLabel("ERROR");
        error1.setBounds(540, 400, 150, 40);
        error1.setFont(fuente1);
        error1.setForeground(new Color(0xE00707));
        error1.setVisible(false);
        ventana2.add(error1);
    }

    public void ventana3() {

        ventana3 = new JPanel();
        ventana3.setLayout(null);
        ventana3.setBackground(new Color(216, 156, 155));
        //Borde personalizado
        ventana3.setBorder(borde);

        //JLabel Título --> ~ Lugar de residencia ~
        crearJLabel(labelResidencia, "~ LUGAR DE RESIDENCIA ~", fuenteTitulo, 0, 100, 920, 50, true, ventana3);

        //JLabel Países
        crearJLabel(labelPaises, "País:", fuente1, 250, 200, 150, 30, true, ventana3);

        //JLabel provincias
        labelProvincias = new JLabel("Provincia:");
        labelProvincias.setFont(fuente1);
        labelProvincias.setBounds(270, 300, 150, 30);
        labelProvincias.setVisible(false);
        ventana3.add(labelProvincias);

        //Jlabel estados
        labelEstados = new JLabel("Estado:");
        labelEstados.setFont(fuente1);
        labelEstados.setBounds(285, 300, 150, 30);
        labelEstados.setVisible(false);
        ventana3.add(labelEstados);

        paises = new JComboBox();
        paises.setBounds(400, 200, 150, 30);
        paises.setBackground(null);

        for (int i = 0; i < paisesArray.length; i++) {
            paises.addItem(paisesArray[i]);
        }

        paises.addActionListener(this);

        ventana3.add(paises);

        //JCombobox Provincias
        provincias = new JComboBox();
        provincias.setBounds(400, 300, 150, 30);
        provincias.setBackground(null);
        provincias.setVisible(false);
        ventana3.add(provincias);

        //JCombobox Estados
        estados = new JComboBox();
        estados.setBounds(400, 300, 150, 30);
        estados.setBackground(null);
        estados.setVisible(false);
        ventana3.add(estados);

        //JButton guardar
        botonGuardar2 = new JButton("Guardar");
        botonGuardar2.setBounds(380, 420, 150, 40);
        botonGuardar2.setFont(fuente1);
        botonGuardar2.addActionListener(this);
        ventana3.add(botonGuardar2);

        paises.addItemListener(this);
        provincias.addItemListener(this);
        estados.addItemListener(this);

        //JLabel correcto
        correcto2 = new JLabel("Correcto");
        correcto2.setBounds(540, 420, 150, 40);
        correcto2.setFont(fuente1);
        correcto2.setForeground(Color.GREEN);
        correcto2.setVisible(false);
        ventana3.add(correcto2);

        //JLabel ERROR
        error2 = new JLabel("ERROR");
        error2.setBounds(540, 420, 150, 40);
        error2.setFont(fuente1);
        error2.setForeground(new Color(0xE00707));
        error2.setVisible(false);
        ventana2.add(error2);


        paises.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if (paises.getSelectedItem().toString().equals("España")) {
                    cargarFicheros(true);
                }
                if (paises.getSelectedItem().toString().equals("EEUU")) {
                    cargarFicheros(false);
                }
            }
        });
    }

    public void ventana4() {

        ventana4 = new JPanel();
        ventana4.setLayout(null);
        ventana4.setBackground(new Color(216, 156, 155));
        //Borde personalizado
        ventana4.setBorder(borde);

        //JLabel Título --> ~ Datos Generados ~
        crearJLabel(labelDatos, "~ DATOS GENERADOS ~", fuenteTitulo, 0, 60, 920, 50, true, ventana4);

        //JTextArea
        textoDatos = new JTextArea();
        textoDatos.setFont(fuente2);
        //textoDatos.setBounds(180, 140, 550, 250);
        //ventana4.add(textoDatos);

        //JCheckbox - Guardar datos
        guardarDatos = new JCheckBox(" Guardar datos en un archivo");
        guardarDatos.setFont(fuente1);
        guardarDatos.setBounds(300, 410, 340, 50);
        guardarDatos.setBackground(null);
        guardarDatos.addActionListener(this);
        ventana4.add(guardarDatos);

        //ScrollPane
        scrollDatos = new JScrollPane(textoDatos);
        scrollDatos.setBounds(180, 140, 550, 250);
        scrollDatos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ventana4.add(scrollDatos);


    }

    public void ventana5() {

        ventana5 = new JPanel();
        ventana5.setLayout(null);
        ventana5.setBackground(new Color(216, 156, 155));
        //Borde personalizado
        ventana5.setBorder(borde);

        //COMPONENTES CREADOS EN hierarchyChanged de ventana 5

    }

    public void cargarFicheros(Boolean condicion) {
        if (condicion == true) {
            labelProvincias.setVisible(true);
            labelEstados.setVisible(false);
            provincias.setVisible(true);
            estados.setVisible(false);
            Scanner entrada = null;
            //JFileChooser fileChooser = new JFileChooser();
            //fileChooser.showOpenDialog(fileChooser);
            try {
                //String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                File f = new File("src/recursos/txt/provincias.txt");
                entrada = new Scanner(f);
                int contador = 0;
                while (entrada.hasNext()) {
                    String res = entrada.nextLine();
                    provinciasArray[contador] = res;
                    provincias.addItem(provinciasArray[contador]);
                    contador++;
                }
                provincias.setSelectedIndex(-1);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("No se ha seleccionado ningún fichero");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (entrada != null) {
                    entrada.close();
                }
            }
        }

        if (condicion == false) {
            labelEstados.setVisible(true);
            labelProvincias.setVisible(false);
            estados.setVisible(true);
            provincias.setVisible(false);
            Scanner entrada = null;
            //JFileChooser fileChooser = new JFileChooser();
            //fileChooser.showOpenDialog(fileChooser);
            try {
                //String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                File f = new File("src/recursos/txt/estados.txt");
                entrada = new Scanner(f);
                int contador = 0;
                while (entrada.hasNext()) {
                    String res = entrada.nextLine();
                    estadosArray[contador] = res;
                    estados.addItem(estadosArray[contador]);
                    contador++;
                }
                estados.setSelectedIndex(-1);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("No se ha seleccionado ningún fichero");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (entrada != null) {
                    entrada.close();
                }
            }
        }
    }

    public boolean validarDatos1() {

        String password = textoContraseña.getText();
        Pattern regex = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,16}$");
        Matcher matcher = regex.matcher(password);


        if (textoNombre.getText() == null) {
            labelNombre.setForeground(Color.RED);
            textoNombre.setForeground(Color.RED);
            return false;
        }
        else if (textoCorreo.getText() == null) {
            labelCorreo.setForeground(Color.RED);
            textoCorreo.setForeground(Color.RED);
            return false;
        }

        else if (!matcher.matches()) {
            labelContraseña.setForeground(Color.RED);
            textoContraseña.setForeground(Color.RED);
            return false;
        }
        else if (!textoCorreo.getText().contains("@")) {
            labelCorreo.setForeground(Color.RED);
            textoCorreo.setForeground(Color.RED);
            return false;
        }
        labelNombre.setForeground(Color.BLACK);
        textoNombre.setForeground(Color.BLACK);
        labelCorreo.setForeground(Color.BLACK);
        textoCorreo.setForeground(Color.BLACK);
        labelContraseña.setForeground(Color.BLACK);
        textoContraseña.setForeground(Color.BLACK);
        return true;
    }

    public boolean validarDatos2() {
        if (paises.getSelectedItem().toString() == "España") {
            if (provincias.getSelectedItem() == null) {
                labelProvincias.setForeground(Color.RED);
                return false;
            }
        }
        if (paises.getSelectedItem().toString() == "EEUU") {
            if (estados.getSelectedItem() == null) {
                labelEstados.setForeground(Color.RED);
                return false;
            }
        }
        return true;
    }

    public void generarDatos() {
        if (validarDatos1() == true && validarDatos2() == true) {
            textoDatos.setText(datos + datos2);
            textoDatos.setEditable(false);
        }

        else if (validarDatos1() == false) {
            textoDatos.setText("ERROR al registrar usuario");
            textoDatos.setForeground(Color.RED);
        }
    }

    public void guardarDatos() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        String ruta;
        try {
            ruta = fileChooser.getSelectedFile().getAbsolutePath();
            PrintWriter writer = new PrintWriter(ruta);
            writer.print(textoDatos.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        if (ruta == null) {
            rutaPrueba = false;
        }

        else {
            rutaPrueba = true;
        }

    }


    public void crearJLabel (JLabel nombre, String texto, Font fuente, int x, int y, int width, int height, Boolean centrar, JPanel panel) {

        nombre = new JLabel(texto);
        nombre.setFont(fuente);
        nombre.setBounds(x, y, width, height);
        if (centrar == true) {
            nombre.setHorizontalAlignment(SwingConstants.CENTER);
        }
        panel.add(nombre);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        p = e.getItem().toString();
        prov = e.getItem().toString();
        est = e.getItem().toString();
    }

    public void enlace(String enlaceAAceder) {
        Desktop enlace = Desktop.getDesktop();
        try {
            enlace.browse(new URI(enlaceAAceder));
        } catch (IOException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        enlace("https://brave.com/terms-of-use/");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void hierarchyChanged(HierarchyEvent e) {

        if (e.getSource() == ventana1) {
            boton2.setVisible(false);
            boton1.setVisible(true);
        }

        if (e.getSource() == ventana2) {
            boton2.setVisible(true);
            if (validarDatos1() == false) {
                boton1.setVisible(false);
            }
        }

        if (e.getSource() == ventana3) {
            boton1.setVisible(false);
            if (validarDatos2() == true) {
                boton1.setVisible(true);
            }
        }

        if (e.getSource() == ventana4) {
            boton1.setVisible(true);
            boton3.setVisible(false);
            generarDatos();
        }

        if (e.getSource() == ventana5) {
            boton1.setVisible(false);
            boton3.setVisible(true);
            if (rutaPrueba == true) {
                JLabel mensaje2 = new JLabel("Datos guardados correctamente");
                mensaje2.setBounds(340, 310, 500, 60);
                mensaje2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
                ventana5.add(mensaje2);

                JLabel gift2 = new JLabel();
                gift2.setBounds(250, 250, 250, 175);
                gift2.setIcon(new javax.swing.ImageIcon("src/recursos/img/comprobar.png"));
                ventana5.add(gift2);
            }

            if (rutaPrueba == false) {
                JLabel mensaje = new JLabel("Los datos no se han guardado");
                mensaje.setBounds(340, 160, 500, 60);
                mensaje.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
                ventana5.add(mensaje);

                JLabel gift = new JLabel();
                gift.setBounds(250, 100, 250, 175);
                gift.setIcon(new javax.swing.ImageIcon("src/recursos/img/Error.png"));
                ventana5.add(gift);
            }
        }
    }

    public class RoundJTextField extends JTextField {
        private Shape shape;
        public RoundJTextField(int size) {
            super(size);
            setOpaque(false);
        }
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
            return shape.contains(x, y);
        }
    }


    public class PanelDegradado extends JPanel {

        Color color1 = new Color(218, 165, 165);
        Color color2 = new Color(211, 35, 12);

        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            Rectangle clip = g2.getClipBounds();
            g2.setPaint(new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2));
            g2.fillRect(clip.x, clip.y, clip.width, clip.height);

            Toolkit t = Toolkit.getDefaultToolkit();
            Image imagen = t.getImage ("src/recursos/img/logo1.png");
            g2.drawImage(imagen, 30, 35, this);
        }
    }


    public static void main(String[] args) {
        new Formulario();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == boton1) {
            if (terminos.isSelected()) {
                ventanas.next(panelVentanas);
            }
            else {
                //Subrayar texto
                Map attributes = fuente1.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                terminos.setFont(fuente1.deriveFont(attributes));
                terminos.setForeground(Color.RED);
            }
        }

        if (e.getSource() == boton2) {
            ventanas.previous(panelVentanas);
        }

        if (e.getSource() == botonGuardar1) {
            if (validarDatos1() == true) {
                boton1.setVisible(true);
                error1.setVisible(false);
                correcto1.setVisible(true);
                datos = "Nombre: " + textoNombre.getText() + "\n" + "Correo: " + textoCorreo.getText() + "\n" +
                        "Contraseña: " + textoContraseña.getText() + "\n" ;
            }
            else {
                boton1.setVisible(false);
                correcto1.setVisible(false);
                error1.setVisible(true);
            }
        }

        if (e.getSource() == botonGuardar2) {
            if (validarDatos2() == true) {
                boton1.setVisible(true);
                error2.setVisible(false);
                correcto2.setVisible(true);
                p = String.valueOf(paises.getSelectedItem());
                prov = String.valueOf(provincias.getSelectedItem());
                est = String.valueOf(estados.getSelectedItem());
                if (paises.getSelectedItem().toString().equals("España")) {
                    datos2 = "País: " + p + "\n" + "Provincia: " + prov;
                }
                else if (paises.getSelectedItem().toString().equals("EEUU")) {
                    datos2 = "País: " + p + "\n" + "Estado: " + est;
                }
            }
            else {
                correcto2.setVisible(false);
                error2.setVisible(true);
            }
        }

        if (e.getSource() == guardarDatos) {
            guardarDatos();
        }

        if (e.getSource() == boton3) {
            System.exit(0);
        }

    }
}