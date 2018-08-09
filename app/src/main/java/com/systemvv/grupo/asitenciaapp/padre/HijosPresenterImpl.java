package com.systemvv.grupo.asitenciaapp.padre;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Instituto;
import com.systemvv.grupo.asitenciaapp.padre.useCase.ObtenerInstituto;
import com.systemvv.grupo.asitenciaapp.padre.useCase.ObtenerMisHijos;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class HijosPresenterImpl extends BaseActivityPresenterImpl<HijosView> implements HijosPresenter {
    public static final String TAG = HijosPresenterImpl.class.getSimpleName();

    ObtenerMisHijos obtenerMisHijos;
    ObtenerInstituto obtenerInstituto;



    public HijosPresenterImpl(UseCaseHandler handler, Resources res, ObtenerMisHijos obtenerMisHijos, ObtenerInstituto obtenerInstituto) {
        super(handler, res);
        this.obtenerMisHijos = obtenerMisHijos;
        this.obtenerInstituto = obtenerInstituto;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    UsuarioUi usuarioUi;
    Instituto instituto;
    String keyPeriodo;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.usuarioUi = Parcels.unwrap(extras.getParcelable("usuarioUi"));
        this.keyPeriodo = usuarioUi.getKeyPeriodo();
        initObtenerInstituto(usuarioUi.getKeyUser());
        //initObtenerHijos(usuarioUi);

    }

    private void initObtenerInstituto(String keyUser) {
        handler.execute(obtenerInstituto, new ObtenerInstituto.RequestValues(usuarioUi),
                new UseCase.UseCaseCallback<ObtenerInstituto.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerInstituto.ResponseValue response) {
                        if (response.getInstituto() == null) {
                            // if(view!=null)view.mostrarmen);
                            Log.d(TAG, "TMR NADA DE INSTITUTOS");
                        } else {
                            instituto = response.getInstituto();
                            initObtenerHijos(usuarioUi);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    private void initObtenerHijos(UsuarioUi usuarioUi) {
        handler.execute(obtenerMisHijos, new ObtenerMisHijos.RequestValues(usuarioUi),
                new UseCase.UseCaseCallback<ObtenerMisHijos.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerMisHijos.ResponseValue response) {
                        if (response.getHijosList() == null) return;
                        for (Hijos hijos : response.getHijosList()) {
                            hijos.setNombreInstituto(instituto.getNombreInstituto());
                            Log.d(TAG, "hijos : " + hijos.getGrado()
                                    + " seccion / " + hijos.getSeccion());
                        }
                        if (view != null) view.mostrarListaHijos(response.getHijosList());

                        //  initObtenerSeccionGrado(response.getHijosList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    private List<Hijos> getListaHijos() {
        List<Hijos> hijosList = new ArrayList<>();

        List<Cursos> cursosList = new ArrayList<>();

        List<Cursos> cursosList2 = new ArrayList<>();

        List<Cursos> cursosList3 = new ArrayList<>();

        Cursos cursosPrimerHijoMatematica = new Cursos("D-MATE", "Matematica", "8:00 a 10:00 am", "Juan Perez", "https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        cursosPrimerHijoMatematica.setIncidenciasList(obtenerListaIncidenciasPrimerHijoCursoMatematica());

        Cursos cursosPrimerHijoComunicacion = new Cursos("D-COMU", "Comunicación", "10:00 a 11:00 am", "Jose Beteta", "https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");
        cursosPrimerHijoComunicacion.setIncidenciasList(obtenerListaIncidenciasPrimerHijoCursoComunicacion());

        Cursos cursosPrimerHijoPersonaSocial = new Cursos("D-SOCI", "Persona Social", "11:30 a 12:30 am", "Juana Lopez", "https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg");
        cursosPrimerHijoPersonaSocial.setIncidenciasList(obtenerListaIncidenciasPrimerHijoCursoPersonaSocial());

        Cursos cursosPrimerHijoComputo = new Cursos("D-COMP", "Computo", "12:00 a 1:00 pm", "Luis Rojas", "https://lh6.googleusercontent.com/-VZ0OG0lXwVk/VN0or214CJI/AAAAAAAAAWg/aZZDUTx3M2E/w984-h209-no/23_piano.jpg");
        Cursos cursosPrimerHijoReligion = new Cursos("D-RELI", "Religión", "1:00 a 1:30 pm", "Kenner Begazo", "https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        Cursos cursosPrimerHijoArte = new Cursos("D-ARTE", "Arte", "1:30 a 2:00 pm", "Rosmeri Guevara", "https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");

        /*Curso de Segundo Hijo*/

        Cursos cursosSegundoHijoMatematica = new Cursos("D-MATE", "Matematica", "8:00 a 10:00 am", "Juan Perez", "https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        cursosSegundoHijoMatematica.setIncidenciasList(obtenerListaIncidenciasSegundoHijoCursoMatematica());

        Cursos cursosSegundoHijoComunicacion = new Cursos("D-COMU", "Comunicación", "10:00 a 11:00 am", "Jose Beteta", "https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");
        cursosSegundoHijoComunicacion.setIncidenciasList(obtenerListaIncidenciasSegundoHijoCursoComunicacion());

        Cursos cursosSegundoHijoPersonaSocial = new Cursos("D-SOCI", "Persona Social", "11:30 a 12:30 am", "Juana Lopez", "https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg");
        Cursos cursosSegundoHijoComputo = new Cursos("D-COMP", "Computo", "12:00 a 1:00 pm", "Luis Rojas", "https://lh6.googleusercontent.com/-VZ0OG0lXwVk/VN0or214CJI/AAAAAAAAAWg/aZZDUTx3M2E/w984-h209-no/23_piano.jpg");
        Cursos cursosSegundoHijoReligion = new Cursos("D-RELI", "Religión", "1:00 a 1:30 pm", "Kenner Begazo", "https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        Cursos cursosSegundoHijoArte = new Cursos("D-ARTE", "Arte", "1:30 a 2:00 pm", "Rosmeri Guevara", "https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");


        Cursos cursosTercerHijoSegundoMatematica = new Cursos("D-MATE", "Matematica", "8:00 a 10:00 am", "Juan Perez", "https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg");
        cursosTercerHijoSegundoMatematica.setIncidenciasList(obtenerListaIncidenciasTercerHijoCursoMatematica());
        Cursos cursosTercerHijoSegundoComunicacion = new Cursos("D-COMU", "Comunicación", "10:00 a 11:00 am", "Jose Beteta", "https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");
        cursosTercerHijoSegundoComunicacion.setIncidenciasList(obtenerListaIncidenciasTercerHijoCursoComunicacion());
        Cursos cursosTercerHijoSegundoPersonaSocial = new Cursos("D-SOCI", "Persona Social", "11:30 a 12:30 am", "Juana Lopez", "https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg");
        Cursos cursosTercerHijoSegundoComputo = new Cursos("D-COMP", "Computo", "12:00 a 1:00 pm", "Luis Rojas", "https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg");


        Hijos primerHijo = new Hijos("1204560", "Andy Manuel", "Vega Soza", "8", "Colegio Peruano - Britanico", "1", "A", "https://tiedralc.files.wordpress.com/2013/03/nino-gafas.jpg");
        primerHijo.setCursosList(cursosList);
        Hijos segundoHijo = new Hijos("1204561", "Mateo Guillermo", "Vega Soza", "8", "Colegio Peruano - Britanico", "3", "A", "http://1.bp.blogspot.com/_wh03KzzmB8s/TKDZvV9ROFI/AAAAAAAAB3g/KXjiCT5JPk0/s1600/josem.JPG");
        segundoHijo.setCursosList(cursosList2);
        Hijos tercerHijo = new Hijos("1204580", "Jean Piere", "Vega Fernandez", "14", "Centro Peruano de Estudios", "6", "B", "http://vientosurnoticias.com.ar/wp-content/uploads/2015/05/Birke-Baehr.png");
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

    private List<Incidencias> obtenerListaIncidenciasTercerHijoCursoComunicacion() {
        List<Incidencias> incidenciasList = new ArrayList<>();
        Incidencias incidencias1 = new Incidencias(1, "El alumno presento un mal comportamiento", "09/07/2017", "10:00", 1);
        Incidencias incidencias2 = new Incidencias(2, "El alumno presento sintomas de fiebre", "09/07/2017", "10:00", 1);
        Incidencias incidencias3 = new Incidencias(3, "El alumno no presta atención en clase", "10/07/2017", "10:00", 2);
        Incidencias incidencias4 = new Incidencias(4, "Habla mucho con su compañero de clase, no hace caso", "12/07/2017", "10:00", 3);
        incidenciasList.add(incidencias1);
        incidenciasList.add(incidencias2);
        incidenciasList.add(incidencias3);
        incidenciasList.add(incidencias4);
        return incidenciasList;
    }

    private List<Incidencias> obtenerListaIncidenciasTercerHijoCursoMatematica() {
        List<Incidencias> incidenciasList = new ArrayList<>();
        Incidencias incidencias1 = new Incidencias(1, "El alumno presento un mal comportamiento", "09/07/2017", "10:00", 1);
        Incidencias incidencias2 = new Incidencias(2, "El alumno presento sintomas de fiebre", "09/07/2017", "10:00", 1);
        incidenciasList.add(incidencias1);
        incidenciasList.add(incidencias2);
        return incidenciasList;
    }

    private List<Incidencias> obtenerListaIncidenciasSegundoHijoCursoComunicacion() {
        List<Incidencias> incidenciasList = new ArrayList<>();
        Incidencias incidencias1 = new Incidencias(1, "El alumno presento un mal comportamiento", "09/07/2017", "10:00", 1);
        Incidencias incidencias2 = new Incidencias(2, "El alumno presento sintomas de fiebre", "09/07/2017", "10:00", 1);
        Incidencias incidencias3 = new Incidencias(3, "Habla mucho con su compañero de clase, no hace caso", "12/07/2017", "10:00", 3);
        incidenciasList.add(incidencias1);
        incidenciasList.add(incidencias2);
        incidenciasList.add(incidencias3);
        return incidenciasList;
    }

    private List<Incidencias> obtenerListaIncidenciasSegundoHijoCursoMatematica() {
        List<Incidencias> incidenciasList = new ArrayList<>();
        Incidencias incidencias1 = new Incidencias(1, "El alumno presento un mal comportamiento", "09/07/2017", "10:00", 1);
        Incidencias incidencias2 = new Incidencias(2, "El alumno presento sintomas de fiebre", "09/07/2017", "10:00", 1);
        Incidencias incidencias3 = new Incidencias(3, "Habla mucho con su compañero de clase, no hace caso", "12/07/2017", "10:00", 3);
        incidenciasList.add(incidencias1);
        incidenciasList.add(incidencias2);
        incidenciasList.add(incidencias3);
        return incidenciasList;
    }

    private List<Incidencias> obtenerListaIncidenciasPrimerHijoCursoPersonaSocial() {
        List<Incidencias> incidenciasList = new ArrayList<>();
        Incidencias incidencias1 = new Incidencias(1, "El alumno presento un mal comportamiento", "09/07/2017", "10:00", 1);
        Incidencias incidencias2 = new Incidencias(2, "El alumno presento sintomas de fiebre", "09/07/2017", "10:00", 1);
        incidenciasList.add(incidencias1);
        incidenciasList.add(incidencias2);
        return incidenciasList;
    }

    private List<Incidencias> obtenerListaIncidenciasPrimerHijoCursoComunicacion() {
        List<Incidencias> incidenciasList = new ArrayList<>();
        Incidencias incidencias1 = new Incidencias(1, "El alumno presento un mal comportamiento", "09/07/2017", "10:00", 1);
        Incidencias incidencias2 = new Incidencias(2, "El alumno presento sintomas de fiebre", "09/07/2017", "10:00", 1);
        Incidencias incidencias3 = new Incidencias(3, "Habla mucho con su compañero de clase, no hace caso", "12/07/2017", "10:00", 3);
        incidenciasList.add(incidencias1);
        incidenciasList.add(incidencias2);
        incidenciasList.add(incidencias3);
        return incidenciasList;
    }

    private List<Incidencias> obtenerListaIncidenciasPrimerHijoCursoMatematica() {

        List<Incidencias> incidenciasList = new ArrayList<>();

        Incidencias incidencias1 = new Incidencias(1, "El alumno presento un mal comportamiento", "09/07/2017", "10:00", 1);
        Incidencias incidencias2 = new Incidencias(2, "El alumno presento sintomas de fiebre", "09/07/2017", "10:00", 1);
        Incidencias incidencias3 = new Incidencias(3, "El alumno no presta atención en clase", "10/07/2017", "10:00", 2);
        Incidencias incidencias4 = new Incidencias(4, "Habla mucho con su compañero de clase, no hace caso", "12/07/2017", "10:00", 3);
        incidenciasList.add(incidencias1);
        incidenciasList.add(incidencias2);
        incidenciasList.add(incidencias3);
        incidenciasList.add(incidencias4);
        return incidenciasList;

    }

    @Override
    public void onStart() {
        super.onStart();
        //if (view != null) view.mostrarListaHijos(getListaHijos());
    }
}
