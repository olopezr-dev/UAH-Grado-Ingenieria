
package poo.PL2.Clases;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import poo.PL2.Interface.*;


public class MainCode {

    
    public static void main(String[] args) throws ClassNotFoundException {
        
        //CARGA LA BASE DE DATOS
        
        // Crear carpetas si no existen
        new File("data/database").mkdirs();
        new File("data/facturas").mkdirs();
        new File("data/imagenesEventos").mkdirs();

        String dbPath = System.getProperty("user.dir") + "/data/database/MiBaseDeDatos.ser";

        // Cargar o inicializar la base de datos
        DataBase dataBase;
        try {
            DataBase.cargar(dbPath);
            System.out.println("Base de datos cargada exitosamente.");
        } catch (IOException e) {
            System.out.println("Creando nueva base de datos...");
            // No necesitamos hacer nada más, el singleton ya está inicializado
        }

        dataBase = DataBase.getInstance();
        
        
        // Cliente 1 - VIP
        Direccion direccion1 = new Direccion("Avenida de la Constitución", "45", "Barcelona", "08010");
        TarjetaCredito tarjeta1 = new TarjetaCredito("María García López", "4539 4512 0398 7356", YearMonth.of(2025, 8));
        Cliente cliente1 = new Cliente("María García", "934567812", direccion1, tarjeta1, true, "mgarcia@gmail.com", "Pass123word");

        // Cliente 2 - No VIP
        Direccion direccion2 = new Direccion("Calle Mayor", "13", "Valencia", "46001");
        TarjetaCredito tarjeta2 = new TarjetaCredito("Juan Martínez Sánchez", "5168 5479 3265 8124", YearMonth.of(2024, 5));
        Cliente cliente2 = new Cliente("Juan Martínez", "963452781", direccion2, tarjeta2, false, "jmartinez@hotmail.com", "Secure99Pass");

        // Cliente 3 - VIP
        Direccion direccion3 = new Direccion("Gran Vía", "28", "Madrid", "28013");
        TarjetaCredito tarjeta3 = new TarjetaCredito("Laura Fernández Castro", "4024 0071 0902 8503", YearMonth.of(2026, 3));
        Cliente cliente3 = new Cliente("Laura Fernández", "917654321", direccion3, tarjeta3, true, "lfernandez@yahoo.com", "LauFer2023!");

        // Cliente 4 - No VIP
        Direccion direccion4 = new Direccion("Calle Real", "7", "Sevilla", "41001");
        TarjetaCredito tarjeta4 = new TarjetaCredito("Carlos Rodríguez Pérez", "3782 8224 6310 005", YearMonth.of(2025, 12));
        Cliente cliente4 = new Cliente("Carlos Rodríguez", "955678432", direccion4, tarjeta4, false, "crodriguez@gmail.com", "Sevilla78Carlos");

        // Cliente 5 - VIP
        Direccion direccion5 = new Direccion("Paseo de la Castellana", "120", "Madrid", "28046");
        TarjetaCredito tarjeta5 = new TarjetaCredito("Ana López Martín", "5579 0765 4321 9876", YearMonth.of(2027, 7));
        Cliente cliente5 = new Cliente("Ana López", "916543287", direccion5, tarjeta5, true, "alopez@outlook.com", "AnaPass2024*");

        // Añadir a la base de datos
        dataBase.addCliente(cliente1);
        dataBase.addCliente(cliente2);
        dataBase.addCliente(cliente3);
        dataBase.addCliente(cliente4);
        dataBase.addCliente(cliente5);
        
        // 1. Concierto en Madrid (pasado, con reseñas)
        ArrayList<Resena> resenasRock = new ArrayList<>();
        resenasRock.add(new Resena(4, "Gran energía pero el sonido podía mejorar", cliente2));
        resenasRock.add(new Resena(5, "¡Espectacular! La mejor noche de mi vida", cliente5));

        dataBase.guardarEvento(new Evento(
            "Concierto Rock 2023",
            "CONCIERTO",
            ValidadorUtilidades.crearFechas("15/11/2022 20:00", "16/11/2022 20:00"), // Pasado
            new Direccion("Calle Gran Vía", "1", "Madrid", "28013"),
            4.5, // Calificación media
            25.50,
            "data/imagenesEventos/rock.png",
            resenasRock
        ));

        // 2. Teatro clásico en Barcelona (futuro, sin reseñas)
        dataBase.guardarEvento(new Evento(
            "Don Juan Tenorio",
            "TEATRO",
            ValidadorUtilidades.crearFechas("20/10/2024 19:30", "21/10/2024 19:30", "22/10/2024 18:00"), // Futuro
            new Direccion("Plaza Catalunya", "5", "Barcelona", "08002"),
            45.00,
            "data/imagenesEventos/teatro.png"
        ));

        // 3. Festival gastronómico en Sevilla (pasado, con reseñas)
        ArrayList<Resena> resenasFeria = new ArrayList<>();
        resenasFeria.add(new Resena(5, "Comida increíble y ambiente fantástico", cliente1));
        resenasFeria.add(new Resena(3, "Demasiada gente, difícil moverse", cliente3));
        resenasFeria.add(new Resena(4, "Muy bien organizado, volveré el próximo año", cliente4));

        dataBase.guardarEvento(new Evento(
            "Feria de Abril",
            "FESTIVAL CULTURAL",
            ValidadorUtilidades.crearFechas("01/04/2023 12:00", "07/04/2023 12:00"), // Pasado
            new Direccion("Calle del Infierno", "s/n", "Sevilla", "41001"),
            4.0, // Calificación media
            10.00,
            "data/imagenesEventos/feria.png",
            resenasFeria
        ));

        // 4. Taller tecnológico en Valencia (pasado, con reseñas)
        ArrayList<Resena> resenasTaller = new ArrayList<>();
        resenasTaller.add(new Resena(5, "Contenido muy actualizado y profesores expertos", cliente5));

        dataBase.guardarEvento(new Evento(
            "Taller de Inteligencia Artificial",
            "TALLER",
            ValidadorUtilidades.crearFechas("05/12/2022 16:00"), // Pasado
            new Direccion("Avenida Blasco Ibáñez", "13", "Valencia", "46010"),
            5.0, // Calificación media
            0.00,
            "data/imagenesEventos/taller.png",
            resenasTaller
        ));

        // 5. Partido de fútbol en Madrid (futuro, sin reseñas)
        dataBase.guardarEvento(new Evento(
            "Real Madrid vs Barcelona",
            "DEPORTE",
            ValidadorUtilidades.crearFechas("12/11/2024 21:00"), // Futuro
            new Direccion("Avda. Concha Espina", "1", "Madrid", "28036"),
            120.00,
            "data/imagenesEventos/futbol.png"
        ));

        // 6. Concierto jazz en Bilbao (pasado, con reseñas)
        ArrayList<Resena> resenasJazz = new ArrayList<>();
        resenasJazz.add(new Resena(4, "Buen ambiente pero corta duración", cliente2));
        resenasJazz.add(new Resena(5, "Músicos increíbles, jazz de primera calidad", cliente4));

        dataBase.guardarEvento(new Evento(
            "Jazz en la Ría",
            "CONCIERTO",
            ValidadorUtilidades.crearFechas("30/09/2022 22:00"), // Pasado
            new Direccion("Plaza Arriaga", "1", "Bilbao", "48005"),
            4.5, // Calificación media
            35.00,
            "data/imagenesEventos/jazz.png",
            resenasJazz
        ));

        // 7. Cine al aire libre en Málaga (pasado, sin reseñas)
        dataBase.guardarEvento(new Evento(
            "Cine de Verano",
            "CINE",
            ValidadorUtilidades.crearFechas("15/07/2022 22:30", "16/07/2022 22:30"), // Pasado
            new Direccion("Paseo Marítimo", "s/n", "Málaga", "29016"),
            5.50,
            "data/imagenesEventos/cine.png"
        ));

        // 8. Musical en Madrid (futuro, sin reseñas)
        dataBase.guardarEvento(new Evento(
            "El Rey León",
            "MUSICAL",
            ValidadorUtilidades.crearFechas("01/12/2024 18:00", "02/12/2024 18:00", "03/12/2024 12:00"), // Futuro
            new Direccion("Calle Jorge Juan", "5", "Madrid", "28009"),
            65.00,
            "data/imagenesEventos/musical.png"
        ));

        // 9. Feria del Libro (pasado, con reseñas)
        ArrayList<Resena> resenasLibros = new ArrayList<>();
        resenasLibros.add(new Resena(5, "Encontré ediciones únicas a buen precio", cliente1));
        resenasLibros.add(new Resena(4, "Interesante pero algunos precios eran altos", cliente3));

        dataBase.guardarEvento(new Evento(
            "Feria del Libro Antiguo",
            "FESTIVAL CULTURAL",
            ValidadorUtilidades.crearFechas("10/11/2022 10:00", "11/11/2022 10:00"), // Pasado
            new Direccion("Plaza Mayor", "s/n", "Valladolid", "47001"),
            4.5, // Calificación media
            8.00,
            "data/imagenesEventos/libros.png",
            resenasLibros
        ));

        // 10. Exposición de Arte (futuro, sin reseñas)
        dataBase.guardarEvento(new Evento(
            "Exposición de Arte Contemporáneo Internacional",
            "FESTIVAL CULTURAL",
            ValidadorUtilidades.crearFechas("05/10/2024 09:00", "30/10/2024 21:00"), // Futuro
            new Direccion("Calle Bailén", "12", "Zaragoza", "50001"),
            12.00,
            "data/imagenesEventos/arte.png"
        ));
        
        // Primero creamos las reservas para los eventos con reseñas

        // 1. Reservas para el Concierto Rock (evento con 2 reseñas)
        Reserva reservaRock1 = new Reserva(
            cliente2, // Juan Martínez
            dataBase.getEventoPorTitulo("Concierto Rock 2023"),
            LocalDateTime.of(2022, 11, 15, 20, 0), // Primera fecha del evento
            2, // 2 entradas
            51.00 // 25.50 x 2
        );

        Reserva reservaRock2 = new Reserva(
            cliente5, // Ana López
            dataBase.getEventoPorTitulo("Concierto Rock 2023"),
            LocalDateTime.of(2022, 11, 16, 20, 0), // Segunda fecha del evento
            4, // 4 entradas
            102.00 // 25.50 x 4
        );

        // 2. Reservas para la Feria de Abril (evento con 3 reseñas)
        Reserva reservaFeria1 = new Reserva(
            cliente1, // María García
            dataBase.getEventoPorTitulo("Feria de Abril"),
            LocalDateTime.of(2023, 4, 3, 12, 0), // Día intermedio del evento
            1, // 1 entrada
            10.00
        );

        Reserva reservaFeria2 = new Reserva(
            cliente3, // Laura Fernández
            dataBase.getEventoPorTitulo("Feria de Abril"),
            LocalDateTime.of(2023, 4, 5, 12, 0),
            2, // 2 entradas
            20.00
        );

        Reserva reservaFeria3 = new Reserva(
            cliente4, // Carlos Rodríguez
            dataBase.getEventoPorTitulo("Feria de Abril"),
            LocalDateTime.of(2023, 4, 7, 12, 0), // Último día
            3, // 3 entradas
            30.00
        );

        // 3. Reserva para el Taller de IA (1 reseña)
        Reserva reservaTaller = new Reserva(
            cliente5, // Ana López
            dataBase.getEventoPorTitulo("Taller de Inteligencia Artificial"),
            LocalDateTime.of(2022, 12, 5, 16, 0),
            1, // Taller individual
            0.00 // Gratuito
        );

        // 4. Reservas para Jazz en la Ría (2 reseñas)
        Reserva reservaJazz1 = new Reserva(
            cliente2, // Juan Martínez
            dataBase.getEventoPorTitulo("Jazz en la Ría"),
            LocalDateTime.of(2022, 9, 30, 22, 0),
            2, // 2 entradas
            70.00 // 35.00 x 2
        );

        Reserva reservaJazz2 = new Reserva(
            cliente4, // Carlos Rodríguez
            dataBase.getEventoPorTitulo("Jazz en la Ría"),
            LocalDateTime.of(2022, 9, 30, 22, 0),
            1, // 1 entrada
            35.00
        );

        // 5. Reservas para Feria del Libro (2 reseñas)
        Reserva reservaLibro1 = new Reserva(
            cliente1, // María García
            dataBase.getEventoPorTitulo("Feria del Libro Antiguo"),
            LocalDateTime.of(2022, 11, 10, 10, 0), // Primer día
            1, // 1 entrada
            8.00
        );

        Reserva reservaLibro2 = new Reserva(
            cliente3, // Laura Fernández
            dataBase.getEventoPorTitulo("Feria del Libro Antiguo"),
            LocalDateTime.of(2022, 11, 11, 10, 0), // Segundo día
            2, // 2 entradas
            16.00
        );

        // Añadir todas las reservas a la base de datos
        dataBase.addReserva(reservaRock1);
        dataBase.addReserva(reservaRock2);
        dataBase.addReserva(reservaFeria1);
        dataBase.addReserva(reservaFeria2);
        dataBase.addReserva(reservaFeria3);
        dataBase.addReserva(reservaTaller);
        dataBase.addReserva(reservaJazz1);
        dataBase.addReserva(reservaJazz2);
        dataBase.addReserva(reservaLibro1);
        dataBase.addReserva(reservaLibro2);

        
        
        
        // TEST: Verificar si hay eventos (solo para depuración)
        System.out.println("Eventos en la base: " + dataBase.getEventos().size());
        
         Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                DataBase.guardar(dbPath);
                System.out.println("Base de datos guardada al cerrar la aplicación.");
            } catch (IOException ex) {
                System.err.println("Error guardando la base de datos: " + ex.getMessage());
            }
        }));
         
         
        // Iniciar la interfaz (sin WindowListener en MainMenu)
        java.awt.EventQueue.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // ¡Importante!
            
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainCode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(MainCode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(MainCode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(MainCode.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            mainMenu.setVisible(true);
            
        });
        
        
    }
}
    

