package com.systemvv.grupo.asitenciaapp.cursos;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class CursoPresenterImpl extends BaseActivityPresenterImpl<CursoView> implements CursoPresenter {
    public static final String TAG = CursoPresenterImpl.class.getSimpleName();

    public CursoPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    int gradoSelected;
    String seccionSelected;
    InstitutoUi institutoUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.gradoSelected = extras.getInt("gradoSelected", 0);
        seccionSelected = extras.getString("seccionSelected");
        institutoUi = Parcels.unwrap(extras.getParcelable("instituto"));
        Log.d(TAG, "institutoUi : " + institutoUi.getNombre() + " / seccionSelected " + seccionSelected + " / " + gradoSelected);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (view != null) view.mostrarListaCursos(getCursoList());
    }

    private List<CursoUi> getCursoList() {
        List<CursoUi> cursoUiList = new ArrayList<>();

        List<AlumnosUi> alumnosUiList = new ArrayList<>();

        List<AsistenciaUi> asistenciaUiLista = new ArrayList<>();
        List<AsistenciaUi> asistenciaUiListPuntuales = new ArrayList<>();
        List<AsistenciaUi> asistenciaUiListTardes = new ArrayList<>();
        List<AsistenciaUi> asistenciaUiListTardeJustificado = new ArrayList<>();
        List<AsistenciaUi> asistenciaUiListFaltaron = new ArrayList<>();

        List<MotivosAsistenciaUi> motivosAsistenciaUis = new ArrayList<>();

        MotivosAsistenciaUi motivosAsistenciaPuntual = new MotivosAsistenciaUi();
        motivosAsistenciaPuntual.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
      /*  asistenciaUiListPuntuales.add(new AsistenciaUi("PUNTUAL","",motivosAsistenciaPuntual));
        asistenciaUiListPuntuales.add(new AsistenciaUi("PUNTUAL","",motivosAsistenciaPuntual));*/

        AsistenciaUi asistenciaPuntual = new AsistenciaUi();
        asistenciaPuntual.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        asistenciaPuntual.setTipasistencia(AsistenciaUi.TipoAsistencia.ASISTENCIA_PRESENTE);
        asistenciaPuntual.setPintar(false);
        asistenciaPuntual.setTipoAsistencia("Puntual");

        AlumnosUi alumnoJuanManuel = new AlumnosUi(1, "Juan Manuel", "Perez Soza", "asd", asistenciaPuntual);
        AlumnosUi alumnoRonald = new AlumnosUi(2, "Jorgue Fernando", "Rodriguez Soza", "asd", asistenciaPuntual);
        AlumnosUi alumnoAlexManuel = new AlumnosUi(3, "Ronald Manuel", "Chambilla Rojas", "asd", asistenciaPuntual);
        AlumnosUi alumnoAndyManuel = new AlumnosUi(4, "Alex Rodolfo", "Aguilar Vera", "asd", asistenciaPuntual);
        AlumnosUi alumnoArturitManuel = new AlumnosUi(5, "Andy Manuel", "Vega Soza", "asd", asistenciaPuntual);
        AlumnosUi alumnoJuanM = new AlumnosUi(6, "Arturito Manuel", "Perez Chancl", "asd", asistenciaPuntual);

        alumnosUiList.add(alumnoJuanManuel);
        alumnosUiList.add(alumnoRonald);
        alumnosUiList.add(alumnoAlexManuel);
        alumnosUiList.add(alumnoAndyManuel);
        alumnosUiList.add(alumnoArturitManuel);
        alumnosUiList.add(alumnoJuanM);

        /*/alumnosUiList.add(new AlumnosUi(1, "Juan Manuel", "Perez Soza", "asd",asistenciaPuntual));
        alumnosUiList.add(new AlumnosUi(2, "Jorgue Fernando", "Rodriguez Soza", "asd",asistenciaPuntual));
        alumnosUiList.add(new AlumnosUi(3, "Ronald Manuel", "Chambilla Rojas", "asd",asistenciaPuntual));
        alumnosUiList.add(new AlumnosUi(4, "Alex Rodolfo", "Aguilar Vera", "asd",asistenciaPuntual));
        alumnosUiList.add(new AlumnosUi(5, "Andy Manuel", "Vega Soza", "asd",asistenciaPuntual));
        alumnosUiList.add(new AlumnosUi(6, "Arturito Manuel", "Perez Chancl", "asd",asistenciaPuntual));
       */
        asistenciaPuntual.setMotivosAsistenciaUi(motivosAsistenciaPuntual);
        asistenciaUiLista.add(asistenciaPuntual);
        motivosAsistenciaPuntual.setAsistenciaUiList(asistenciaUiListPuntuales);


        MotivosAsistenciaUi motivosAsistenciaTardeUi = new MotivosAsistenciaUi();
        motivosAsistenciaTardeUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);

        AsistenciaUi asistenciaTarde = new AsistenciaUi();
        asistenciaTarde.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);
        asistenciaTarde.setTipasistencia(AsistenciaUi.TipoAsistencia.ASISTENCIA_TARDE);
        asistenciaTarde.setTipoAsistencia("Tarde");
        asistenciaTarde.setPintar(false);

        AlumnosUi alumnoPedroJuan = new AlumnosUi(7, "Pedro Manuel", "Perez Soza", "asd", asistenciaTarde);
        AlumnosUi alumnoJuanMa = new AlumnosUi(8, "Juan Manuel", "Perez Soza", "asd", asistenciaTarde);
        alumnosUiList.add(alumnoPedroJuan);
        alumnosUiList.add(alumnoJuanMa);
        // alumnosUiList.add(new AlumnosUi(7, "Pedro Manuel", "Perez Soza", "asd", asistenciaTarde));
        //alumnosUiList.add(new AlumnosUi(8, "Juan Manuel", "Perez Soza", "asd", asistenciaTarde));/* asistenciaUiListTardes.add(new AsistenciaUi("TARDE","",motivosAsistenciaTardeUi));

       /* asistenciaUiListTardes.add(new AsistenciaUi("TARDE","",motivosAsistenciaTardeUi));
        asistenciaUiListTardes.add(new AsistenciaUi("TARDE","",motivosAsistenciaTardeUi));
        asistenciaUiListTardes.add(new AsistenciaUi("TARDE","",motivosAsistenciaTardeUi));*/

        asistenciaTarde.setMotivosAsistenciaUi(motivosAsistenciaTardeUi);
        asistenciaUiLista.add(asistenciaTarde);
        motivosAsistenciaTardeUi.setAsistenciaUiList(asistenciaUiListTardes);


        MotivosAsistenciaUi motivosAsistenciaTardeJustificadoUi = new MotivosAsistenciaUi();
        motivosAsistenciaTardeJustificadoUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO);

        AsistenciaUi asistenciaTardeJustificado = new AsistenciaUi();
        asistenciaTardeJustificado.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO);
        asistenciaTardeJustificado.setTipasistencia(AsistenciaUi.TipoAsistencia.ASISTENCIA_TARDE);
        asistenciaTardeJustificado.setTipoAsistencia("Justificado");
        asistenciaTardeJustificado.setPintar(false);

        AlumnosUi alumnoRobertol = new AlumnosUi(9, "Roberto Manuel", "Perez Soza", "asd", asistenciaTardeJustificado);
        alumnosUiList.add(alumnoRobertol);
        //alumnosUiList.add(new AlumnosUi(9, "Juan Manuel", "Perez Soza", "asd", asistenciaTardeJustificado));

       /* asistenciaUiListTardeJustificado.add(new AsistenciaUi("TARDE_JUSTIFICADO","",motivosAsistenciaTardeJustificadoUi));
        asistenciaUiListTardeJustificado.add(new AsistenciaUi("TARDE_JUSTIFICADO","",motivosAsistenciaTardeJustificadoUi));*/

        asistenciaTardeJustificado.setMotivosAsistenciaUi(motivosAsistenciaTardeJustificadoUi);
        asistenciaUiLista.add(asistenciaTardeJustificado);
        motivosAsistenciaTardeJustificadoUi.setAsistenciaUiList(asistenciaUiListTardeJustificado);



        MotivosAsistenciaUi motivosAsistenciaFaltoUi = new MotivosAsistenciaUi();
        motivosAsistenciaFaltoUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);

        AsistenciaUi asistenciaFalto = new AsistenciaUi();
        asistenciaFalto.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);
        asistenciaFalto.setTipasistencia(AsistenciaUi.TipoAsistencia.ASISTENCIA_FALTO);
        asistenciaFalto.setTipoAsistencia("Falto");
        asistenciaFalto.setPintar(false);



        //  alumnosUiList.add(new AlumnosUi(10, "Juan Manuel", "Perez Soza", "asd", asistenciaFalto));
       /* asistenciaUiListFaltaron.add(new AsistenciaUi("FALTO","",motivosAsistenciaFaltoUi));
        asistenciaUiListFaltaron.add(new AsistenciaUi("FALTO","",motivosAsistenciaFaltoUi));*/


       // motivosAsistenciaFaltoUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);

        AlumnosUi alumnoUltimo = new AlumnosUi(10, "Juan Manuel", "Perez Soza", "asd", asistenciaFalto);
        alumnosUiList.add(alumnoUltimo);

        asistenciaFalto.setMotivosAsistenciaUi(motivosAsistenciaFaltoUi);
        asistenciaUiLista.add(asistenciaFalto);
        motivosAsistenciaFaltoUi.setAsistenciaUiList(asistenciaUiListFaltaron);


        motivosAsistenciaUis.add(motivosAsistenciaPuntual);
        motivosAsistenciaUis.add(motivosAsistenciaTardeUi);
        motivosAsistenciaUis.add(motivosAsistenciaTardeJustificadoUi);
        motivosAsistenciaUis.add(motivosAsistenciaFaltoUi);

        CursoUi cursoMatematica = new CursoUi("Matematica", seccionSelected, gradoSelected, "https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg", institutoUi, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);
        CursoUi cursoReligion = new CursoUi("Religion", seccionSelected, gradoSelected, "https://lh4.googleusercontent.com/-JVP8B-wKf-o/VN0omKitxJI/AAAAAAAAAUQ/TbGF8BU0OVs/w984-h209-no/149_world_googleblue.jpg", institutoUi, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);
        CursoUi cursoPersonaSocial = new CursoUi("Persona Social", seccionSelected, gradoSelected, "https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg", institutoUi, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);
        CursoUi cursoRazonaMiento = new CursoUi("Computación", seccionSelected, gradoSelected, "https://lh6.googleusercontent.com/-VZ0OG0lXwVk/VN0or214CJI/AAAAAAAAAWg/aZZDUTx3M2E/w984-h209-no/23_piano.jpg", institutoUi, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);
        CursoUi cursoComunicacion = new CursoUi("Comunicación", seccionSelected, gradoSelected, "https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg", institutoUi, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);

        cursoUiList.add(cursoMatematica);
        cursoUiList.add(cursoReligion);
        cursoUiList.add(cursoPersonaSocial);
        cursoUiList.add(cursoRazonaMiento);
        cursoUiList.add(cursoComunicacion);
        return cursoUiList;
    }
}
