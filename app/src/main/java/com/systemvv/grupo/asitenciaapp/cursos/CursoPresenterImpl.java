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
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
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
  //  InstitutoUi institutoUi;
    SeccionUi seccionUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
       /* this.gradoSelected = extras.getInt("gradoSelected", 0);
        seccionSelected = extras.getString("seccionSelected");*/
        //institutoUi = Parcels.unwrap(extras.getParcelable("instituto"));
        this.seccionUi= Parcels.unwrap(extras.getParcelable("seccionUi"));
        Log.d(TAG, "institutoUi : " + seccionUi.getInstitutoUi().getNombre() );
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


        AsistenciaUi asistenciaPuntual = new AsistenciaUi();
        asistenciaPuntual.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        asistenciaPuntual.setMotivosAsistenciaUi(motivosAsistenciaPuntual);
        asistenciaPuntual.setPintar(false);
        asistenciaPuntual.setTipoAsistencia("Puntual");

        AlumnosUi alumnoJuanManuel = new AlumnosUi(1, "Jorgue Manuel", "Perez Soza", "https://tiedralc.files.wordpress.com/2013/03/nino-gafas.jpg", asistenciaPuntual);
        asistenciaPuntual.setAlumnosUi(alumnoJuanManuel);
        alumnoJuanManuel.setAsistenciaUi(asistenciaPuntual);
        alumnoJuanManuel.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        alumnoJuanManuel.setTipoPadecimiento(0);


        AlumnosUi alumnoRonald = new AlumnosUi(2, "Lizbeth Eliza", "Rodriguez Soza", "https://tiedralc.files.wordpress.com/2013/03/nino-sin-gafas.jpg", asistenciaPuntual);
        asistenciaPuntual.setAlumnosUi(alumnoRonald);
        alumnoRonald.setAsistenciaUi(asistenciaPuntual);
        alumnoRonald.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        alumnoRonald.setTipoPadecimiento(0);


        AlumnosUi alumnoAlexManuel = new AlumnosUi(3, "Maria Fernanda ", "Chambilla Rojas", "https://lh4.googleusercontent.com/-YEAuX1F0AiE/TW6WB1Ve_PI/AAAAAAAADEs/K-u36P-xzZ8/s1600/cats.jpg", asistenciaPuntual);
        asistenciaPuntual.setAlumnosUi(alumnoAlexManuel);
        alumnoAlexManuel.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        alumnoAlexManuel.setTipoPadecimiento(1);


        AlumnosUi alumnoAndyManuel = new AlumnosUi(4, "Alex Rodolfo", "Aguilar Vera", "https://lh5.googleusercontent.com/-d5pBHBjNdxk/TYe2xBopLlI/AAAAAAAADUM/yAcg7udW6T0/s1600/hugo+n.JPG", asistenciaPuntual);
        asistenciaPuntual.setAlumnosUi(alumnoAndyManuel);
        alumnoAndyManuel.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        alumnoAndyManuel.setTipoPadecimiento(0);


        AlumnosUi alumnoArturitManuel = new AlumnosUi(5, "Andy Manuel", "Vega Soza", "https://lh6.googleusercontent.com/-zO_nrglt9M8/TXfNjwZbbfI/AAAAAAAADKk/cKZ4mxXS150/s1600/hugom.JPG", asistenciaPuntual);
        asistenciaPuntual.setAlumnosUi(alumnoArturitManuel);
        alumnoArturitManuel.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        asistenciaPuntual.setAlumnosUi(alumnoArturitManuel);
        alumnoArturitManuel.setTipoPadecimiento(0);


        AlumnosUi alumnoJuanM = new AlumnosUi(6, "Arturito Manuel", "Perez Chancl", "https://www.parentcenterhub.org/wp-content/uploads/2010/08/rsz_hispboy-six.jpg", asistenciaPuntual);
        asistenciaPuntual.setAlumnosUi(alumnoJuanM);
        alumnoJuanM.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        alumnoJuanM.setTipoPadecimiento(2);

        asistenciaUiListPuntuales.add(asistenciaPuntual);

        alumnosUiList.add(alumnoJuanManuel);
        alumnosUiList.add(alumnoRonald);
        alumnosUiList.add(alumnoAlexManuel);
        alumnosUiList.add(alumnoAndyManuel);
        alumnosUiList.add(alumnoArturitManuel);
        alumnosUiList.add(alumnoJuanM);


        asistenciaPuntual.setMotivosAsistenciaUi(motivosAsistenciaPuntual);
        asistenciaUiLista.add(asistenciaPuntual);
        motivosAsistenciaPuntual.setAsistenciaUiList(asistenciaUiListPuntuales);


        MotivosAsistenciaUi motivosAsistenciaTardeUi = new MotivosAsistenciaUi();
        motivosAsistenciaTardeUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);

        AsistenciaUi asistenciaTarde = new AsistenciaUi();
        asistenciaTarde.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);
        // asistenciaTarde.setTipasistencia(AsistenciaUi.TipoAsistencia.ASISTENCIA_TARDE);
        asistenciaTarde.setTipoAsistencia("Tarde");
        asistenciaTarde.setPintar(false);

        AlumnosUi alumnoPedroJuan = new AlumnosUi(7, "Estefany Valentina", "Perez Soza", "http://lainmaculada.pe/website/wp-content/uploads/2016/02/ninosinicial16-1024x672.jpg", asistenciaTarde);
        alumnoPedroJuan.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);
        asistenciaTarde.setAlumnosUi(alumnoPedroJuan);
        alumnoPedroJuan.setTipoPadecimiento(0);

        AlumnosUi alumnoJuanMa = new AlumnosUi(8, "Juan Manuel", "Perez Soza", "http://lainmaculada.pe/website/wp-content/uploads/2016/02/ninosinicial18-1024x672.jpg", asistenciaTarde);
        asistenciaTarde.setAlumnosUi(alumnoJuanMa);
        alumnoJuanMa.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);
        alumnoJuanMa.setTipoPadecimiento(0);


        alumnosUiList.add(alumnoPedroJuan);
        alumnosUiList.add(alumnoJuanMa);

        asistenciaTarde.setMotivosAsistenciaUi(motivosAsistenciaTardeUi);
        asistenciaUiLista.add(asistenciaTarde);
        asistenciaUiListTardes.add(asistenciaTarde);
        motivosAsistenciaTardeUi.setAsistenciaUiList(asistenciaUiListTardes);




      /*  MotivosAsistenciaUi motivosAsistenciaTardeJustificadoUi = new MotivosAsistenciaUi();
        motivosAsistenciaTardeJustificadoUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO);

        AsistenciaUi asistenciaTardeJustificado = new AsistenciaUi();
        asistenciaTardeJustificado.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO);
      //  asistenciaTardeJustificado.setTipasistencia(AsistenciaUi.TipoAsistencia.ASISTENCIA_TARDE);
        asistenciaTardeJustificado.setTipoAsistencia("Justificado");
        asistenciaTardeJustificado.setPintar(false);*/

        AlumnosUi alumnoRobertol = new AlumnosUi(9, "Roberto Manuel", "Perez Soza", "http://ilumina2photo.es/wp-content/uploads/2016/02/fotografo-infantil-barcelona-reportaje-fotos-ni%C3%B1os-campo-sesion-fotografica-estudio-profesional-008.jpg", null);
        alumnoRobertol.setTipoPadecimiento(0);
        alumnoRobertol.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO);
        alumnosUiList.add(alumnoRobertol);





        MotivosAsistenciaUi motivosAsistenciaFaltoUi = new MotivosAsistenciaUi();
        motivosAsistenciaFaltoUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);

        AsistenciaUi asistenciaFalto = new AsistenciaUi();
        asistenciaFalto.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);

        asistenciaFalto.setTipoAsistencia("Falto");
        asistenciaFalto.setPintar(false);



        AlumnosUi alumnoUltimo = new AlumnosUi(10, "Juan Pepito", "Perez Soza", "http://ilumina2photo.es/wp-content/uploads/2016/02/sesion-fotos-ni%C3%B1os-playa-barcelona-infantil-reportaje-fotografia-costa-ni%C3%B1o-ni%C3%B1a-aire-libre-2.jpg", asistenciaFalto);
        alumnoUltimo.setTipoAsistencia(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);
        alumnoUltimo.setTipoPadecimiento(0);
        alumnosUiList.add(alumnoUltimo);

        asistenciaFalto.setMotivosAsistenciaUi(motivosAsistenciaFaltoUi);
        asistenciaUiLista.add(asistenciaFalto);
        asistenciaUiListFaltaron.add(asistenciaFalto);
        motivosAsistenciaFaltoUi.setAsistenciaUiList(asistenciaUiListFaltaron);


        motivosAsistenciaUis.add(motivosAsistenciaPuntual);
        motivosAsistenciaUis.add(motivosAsistenciaTardeUi);
        motivosAsistenciaUis.add(motivosAsistenciaFaltoUi);


        /*Lista Cursos*/
        CursoUi cursoMatematica = new CursoUi("Matematica", seccionSelected, gradoSelected, "https://lh4.googleusercontent.com/-BM1HpAOAH8s/VN0opWfgsNI/AAAAAAAAAVc/xbsgs7KRG_o/w984-h209-no/162_bookshelf_brown.jpg", null, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);
        CursoUi cursoReligion = new CursoUi("Religion", seccionSelected, gradoSelected, "https://lh4.googleusercontent.com/-JVP8B-wKf-o/VN0omKitxJI/AAAAAAAAAUQ/TbGF8BU0OVs/w984-h209-no/149_world_googleblue.jpg", null, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);
        CursoUi cursoPersonaSocial = new CursoUi("Persona Social", seccionSelected, gradoSelected, "https://lh6.googleusercontent.com/-7Ww0toY0P1s/VN0op12wi-I/AAAAAAAAAVs/GUcBQ4yvdPw/w984-h209-no/164_windows_ltblue.jpg", null, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);
        CursoUi cursoRazonaMiento = new CursoUi("Computación", seccionSelected, gradoSelected, "https://lh6.googleusercontent.com/-VZ0OG0lXwVk/VN0or214CJI/AAAAAAAAAWg/aZZDUTx3M2E/w984-h209-no/23_piano.jpg", null, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);
        CursoUi cursoComunicacion = new CursoUi("Comunicación", seccionSelected, gradoSelected, "https://lh5.googleusercontent.com/-Fu7AEy1bRQs/VN0ojktkA4I/AAAAAAAAATE/73rXQ2D-iR0/w984-h209-no/13_drops.jpg", null, alumnosUiList, motivosAsistenciaUis,asistenciaUiLista);

        cursoUiList.add(cursoMatematica);
        cursoUiList.add(cursoReligion);
        cursoUiList.add(cursoPersonaSocial);
        cursoUiList.add(cursoRazonaMiento);
        cursoUiList.add(cursoComunicacion);
        return cursoUiList;
    }
}
