package com.systemvv.grupo.asitenciaapp.padre;

import android.content.res.Resources;

import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;

import java.util.ArrayList;
import java.util.List;

public class HijosPresenterImpl extends BaseActivityPresenterImpl<HijosView> implements HijosPresenter {
    public static final String TAG = HijosPresenterImpl.class.getSimpleName();

    public HijosPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    private List<Hijos> getListaHijos() {
        List<Hijos> hijosList = new ArrayList<>();

        List<Cursos> cursosList = new ArrayList<>();

        List<Cursos> cursosList2 = new ArrayList<>();

        Cursos cursosPrimerHijoSegundoMatematica = new Cursos("D-MATE", "Matematica", "8:00 a 10:00 am", "Juan Perez","https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");

        Cursos cursosPrimerHijoSegundoComunicacion = new Cursos("D-COMU", "Comunicación", "10:00 a 11:00 am", "Jose Beteta","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");
        Cursos cursosPrimerHijoSegundoPersonaSocial = new Cursos("D-SOCI", "Persona Social", "11:30 a 12:30 am", "Juana Lopez","https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg");
        Cursos cursosPrimerHijoSegundoComputo = new Cursos("D-COMP", "Computo", "12:00 a 1:00 pm", "Luis Rojas","https://lh6.googleusercontent.com/-VZ0OG0lXwVk/VN0or214CJI/AAAAAAAAAWg/aZZDUTx3M2E/w984-h209-no/23_piano.jpg");
        Cursos cursosPrimerHijoSegundoReligion = new Cursos("D-RELI", "Religión", "1:00 a 1:30 pm", "Kenner Begazo","https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        Cursos cursosPrimerHijoSegundoArte = new Cursos("D-ARTE", "Arte", "1:30 a 2:00 pm", "Rosmeri Guevara","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");







        Cursos cursosTercerHijoSegundoMatematica = new Cursos("D-MATE", "Matematica", "8:00 a 10:00 am", "Juan Perez","https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        Cursos cursosTercerHijoSegundoComunicacion = new Cursos("D-COMU", "Comunicación", "10:00 a 11:00 am", "Jose Beteta","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");
        Cursos cursosTercerHijoSegundoPersonaSocial = new Cursos("D-SOCI", "Persona Social", "11:30 a 12:30 am", "Juana Lopez","https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg");
        Cursos cursosTercerHijoSegundoComputo = new Cursos("D-COMP", "Computo", "12:00 a 1:00 pm", "Luis Rojas","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");


        Hijos primerHijo = new Hijos("1204560", "Andy Manuel", "Vega Soza", "8", "Colegio Peruano - Britanico", "3", "A", "http://ilumina2photo.es/wp-content/uploads/2016/03/fotografos-barcelona-reportaje-fotos-infantil-cumplea%C3%B1os-sesion-fotografica-familiar-exterior-pareja-fotografo-familia.jpg");
        primerHijo.setCursosList(cursosList);
        Hijos segundoHijo = new Hijos("1204561", "Mateo Guillermo", "Vega Soza", "8", "Colegio Peruano - Britanico", "3", "A", "http://ilumina2photo.es/wp-content/uploads/2016/03/fotografos-barcelona-reportaje-fotos-infantil-cumplea%C3%B1os-sesion-fotografica-familiar-exterior-pareja-fotografo-familia.jpg");
        segundoHijo.setCursosList(cursosList);
        Hijos tercerHijo = new Hijos("1204580", "Jean Piere", "Vega Fernandez", "14", "Centro Peruano de Estudios", "6", "B", "http://ilumina2photo.es/wp-content/uploads/2016/02/sesion-fotos-ni%C3%B1os-playa-barcelona-infantil-reportaje-fotografia-costa-ni%C3%B1o-ni%C3%B1a-aire-libre-2.jpg");
        tercerHijo.setCursosList(cursosList2);

        cursosPrimerHijoSegundoMatematica.setHijos(primerHijo);
        cursosPrimerHijoSegundoComunicacion.setHijos(primerHijo);
        cursosPrimerHijoSegundoPersonaSocial.setHijos(primerHijo);
        cursosPrimerHijoSegundoComputo.setHijos(primerHijo);
        cursosPrimerHijoSegundoReligion.setHijos(primerHijo);
        cursosPrimerHijoSegundoArte.setHijos(primerHijo);


        cursosTercerHijoSegundoMatematica.setHijos(tercerHijo);
        cursosTercerHijoSegundoComunicacion.setHijos(tercerHijo);
        cursosTercerHijoSegundoPersonaSocial.setHijos(tercerHijo);
        cursosTercerHijoSegundoComputo.setHijos(tercerHijo);



        cursosList.add(cursosPrimerHijoSegundoMatematica);
        cursosList.add(cursosPrimerHijoSegundoComunicacion);
        cursosList.add(cursosPrimerHijoSegundoPersonaSocial);
        cursosList.add(cursosPrimerHijoSegundoComputo);
        cursosList.add(cursosPrimerHijoSegundoReligion);
        cursosList.add(cursosPrimerHijoSegundoArte);


        cursosList2.add(cursosTercerHijoSegundoMatematica);
        cursosList2.add(cursosTercerHijoSegundoComunicacion);
        cursosList2.add(cursosTercerHijoSegundoPersonaSocial);
        cursosList2.add(cursosTercerHijoSegundoComputo);





        hijosList.add(primerHijo);
        hijosList.add(segundoHijo);
        hijosList.add(tercerHijo);
        return hijosList;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (view != null) view.mostrarListaHijos(getListaHijos());
    }
}