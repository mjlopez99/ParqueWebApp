package otros;

import jakarta.inject.Inject;

public class MainApp {

    @Inject
    private JPQLQueryBean jpqlQueryBean;

    public static void main(String[] args) {
        // Asegúrate de que jpqlQueryBean esté inicializado antes de llamar al método
        MainApp mapp=new MainApp();
        if (mapp.jpqlQueryBean != null) {
            mapp.jpqlQueryBean.ejecutarConsulta();
        } else {
            System.out.println("jpqlQueryBean es nulo. Asegúrate de que esté correctamente inicializado.");
        }
    }
}
