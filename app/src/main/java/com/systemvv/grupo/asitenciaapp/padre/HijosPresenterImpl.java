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

        List<Cursos> cursosList3 = new ArrayList<>();

        Cursos cursosPrimerHijoMatematica = new Cursos("D-MATE", "Matematica", "8:00 a 10:00 am", "Juan Perez","https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");

        Cursos cursosPrimerHijoComunicacion = new Cursos("D-COMU", "Comunicación", "10:00 a 11:00 am", "Jose Beteta","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");
        Cursos cursosPrimerHijoPersonaSocial = new Cursos("D-SOCI", "Persona Social", "11:30 a 12:30 am", "Juana Lopez","https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg");
        Cursos cursosPrimerHijoComputo = new Cursos("D-COMP", "Computo", "12:00 a 1:00 pm", "Luis Rojas","https://lh6.googleusercontent.com/-VZ0OG0lXwVk/VN0or214CJI/AAAAAAAAAWg/aZZDUTx3M2E/w984-h209-no/23_piano.jpg");
        Cursos cursosPrimerHijoReligion = new Cursos("D-RELI", "Religión", "1:00 a 1:30 pm", "Kenner Begazo","https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        Cursos cursosPrimerHijoArte = new Cursos("D-ARTE", "Arte", "1:30 a 2:00 pm", "Rosmeri Guevara","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");



        Cursos cursosSegundoHijoMatematica = new Cursos("D-MATE", "Matematica", "8:00 a 10:00 am", "Juan Perez","https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");

        Cursos cursosSegundoHijoComunicacion = new Cursos("D-COMU", "Comunicación", "10:00 a 11:00 am", "Jose Beteta","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");
        Cursos cursosSegundoHijoPersonaSocial = new Cursos("D-SOCI", "Persona Social", "11:30 a 12:30 am", "Juana Lopez","https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg");
        Cursos cursosSegundoHijoComputo = new Cursos("D-COMP", "Computo", "12:00 a 1:00 pm", "Luis Rojas","https://lh6.googleusercontent.com/-VZ0OG0lXwVk/VN0or214CJI/AAAAAAAAAWg/aZZDUTx3M2E/w984-h209-no/23_piano.jpg");
        Cursos cursosSegundoHijoReligion = new Cursos("D-RELI", "Religión", "1:00 a 1:30 pm", "Kenner Begazo","https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        Cursos cursosSegundoHijoArte = new Cursos("D-ARTE", "Arte", "1:30 a 2:00 pm", "Rosmeri Guevara","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");







        Cursos cursosTercerHijoSegundoMatematica = new Cursos("D-MATE", "Matematica", "8:00 a 10:00 am", "Juan Perez","https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        Cursos cursosTercerHijoSegundoComunicacion = new Cursos("D-COMU", "Comunicación", "10:00 a 11:00 am", "Jose Beteta","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");
        Cursos cursosTercerHijoSegundoPersonaSocial = new Cursos("D-SOCI", "Persona Social", "11:30 a 12:30 am", "Juana Lopez","https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg");
        Cursos cursosTercerHijoSegundoComputo = new Cursos("D-COMP", "Computo", "12:00 a 1:00 pm", "Luis Rojas","https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");


        Hijos primerHijo = new Hijos("1204560", "Andy Manuel", "Vega Soza", "8", "Colegio Peruano - Britanico", "3", "A", "http://ilumina2photo.es/wp-content/uploads/2016/03/fotografos-barcelona-reportaje-fotos-infantil-cumplea%C3%B1os-sesion-fotografica-familiar-exterior-pareja-fotografo-familia.jpg");
        primerHijo.setCursosList(cursosList);
        Hijos segundoHijo = new Hijos("1204561", "Mateo Guillermo", "Vega Soza", "8", "Colegio Peruano - Britanico", "3", "A", "http://ilumina2photo.es/wp-content/uploads/2016/03/fotografos-barcelona-reportaje-fotos-infantil-cumplea%C3%B1os-sesion-fotografica-familiar-exterior-pareja-fotografo-familia.jpg");
        segundoHijo.setCursosList(cursosList);
        Hijos tercerHijo = new Hijos("1204580", "Jean Piere", "Vega Fernandez", "14", "Centro Peruano de Estudios", "6", "B", "http://ilumina2photo.es/wp-content/uploads/2016/02/sesion-fotos-ni%C3%B1os-playa-barcelona-infantil-reportaje-fotografia-costa-ni%C3%B1o-ni%C3%B1a-aire-libre-2.jpg");
        tercerHijo.setCursosList(cursosList3);

        cursosPrimerHijoMatematica.setHijos(primerHijo);
        cursosPrimerHijoComunicacion.setHijos(primerHijo);
        cursosPrimerHijoPersonaSocial.setHijos(primerHijo);
        cursosPrimerHijoComputo.setHijos(primerHijo);
        cursosPrimerHijoReligion.setHijos(primerHijo);
        cursosPrimerHijoArte.setHijos(primerHijo);

        cursosSegundoHijoMatematica.setHijos(segundoHijo);
        cursosSegundoHijoComunicacion.setHijos(segundoHijo);
        cursosSegundoHijoPersonaSocial.setHijos(segundoHijo);
        cursosSegundoHijoComputo.setHijos(segundoHijo);
        cursosSegundoHijoReligion.setHijos(segundoHijo);
        cursosSegundoHijoArte.setHijos(segundoHijo);


        cursosTercerHijoSegundoMatematica.setHijos(tercerHijo);
        cursosTercerHijoSegundoComunicacion.setHijos(tercerHijo);
        cursosTercerHijoSegundoPersonaSocial.setHijos(tercerHijo);
        cursosTercerHijoSegundoComputo.setHijos(tercerHijo);



        cursosList.add(cursosPrimerHijoMatematica);
        cursosList.add(cursosPrimerHijoComunicacion);
        cursosList.add(cursosPrimerHijoPersonaSocial);
        cursosList.add(cursosPrimerHijoComputo);
        cursosList.add(cursosPrimerHijoReligion);
        cursosList.add(cursosPrimerHijoArte);


        cursosList2.add(cursosSegundoHijoMatematica);
        cursosList2.add(cursosSegundoHijoComunicacion);
        cursosList2.add(cursosSegundoHijoPersonaSocial);
        cursosList2.add(cursosSegundoHijoComputo);
        cursosList2.add(cursosSegundoHijoReligion);
        cursosList2.add(cursosSegundoHijoArte);


        cursosList3.add(cursosTercerHijoSegundoMatematica);
        cursosList3.add(cursosTercerHijoSegundoComunicacion);
        cursosList3.add(cursosTercerHijoSegundoPersonaSocial);
        cursosList3.add(cursosTercerHijoSegundoComputo);





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
