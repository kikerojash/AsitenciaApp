package com.systemvv.grupo.asitenciaapp.seleccionarInstituto;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.SeccionUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.useCase.ObtenerInstitutoLista;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class InstitutoPresenterImpl extends BaseActivityPresenterImpl<InstitutoView> implements InstitutoPresenter {

    public static final String TAG = InstitutoPresenterImpl.class.getSimpleName();

    private ObtenerInstitutoLista obtenerInstitutoLista;

    public InstitutoPresenterImpl(UseCaseHandler handler, Resources res, ObtenerInstitutoLista obtenerInstitutoLista) {
        super(handler, res);
        this.obtenerInstitutoLista = obtenerInstitutoLista;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        obtenerDatosDocente();
    }

    private void obtenerDatosDocente() {
    }

    String keyPeriodo;
    UsuarioUi usuarioUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.usuarioUi = Parcels.unwrap(extras.getParcelable("usuarioUi"));
        this.keyPeriodo = extras.getString("keyPeriodo");
        Log.d(TAG, "usuario : " + usuarioUi.getUsu_email() + " / keyPeriodo : " + keyPeriodo);
    }

    @Override
    public void onStart() {
        super.onStart();
        mostrarListaInstitutos(keyPeriodo);
        // if (view != null) view.mostrarListaInstitutos(insertDataStatica());
    }

    private void mostrarListaInstitutos(final String keyPeriodo) {
        if (view != null) view.mostrarProgressBar();
        handler.execute(obtenerInstitutoLista, new ObtenerInstitutoLista.RequestValues(keyPeriodo),
                new UseCase.UseCaseCallback<ObtenerInstitutoLista.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerInstitutoLista.ResponseValue response) {
                        if (view != null) {
                            for(InstitutoUi institutoUi:response.getInstitutoUiList()){
                                institutoUi.setKeyPeriodo(keyPeriodo);
                                institutoUi.setKeyUsuario(usuarioUi.getKeyUser());
                            }
                            view.mostrarListaInstitutos(response.getInstitutoUiList());
                            //view.mostrarListaInstitutos(insertDataStatica());
                            view.ocultarProgressBar();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private List<InstitutoUi> insertDataStatica() {
        List<InstitutoUi> institutoUis = new ArrayList<>();

        InstitutoUi institutoSanMiguelUi = new InstitutoUi();
        institutoSanMiguelUi.setNombre("CENTRO PERUANO DE ESTUDIOS BANCARIOS");
        institutoSanMiguelUi.setImage("https://pbs.twimg.com/profile_images/458619559239352321/fA3yrNoY_400x400.png");
        institutoSanMiguelUi.setCede("Lince");
        institutoSanMiguelUi.setDireccion("Av. Arequipa N° 2661");
        List<SeccionUi> seccionUiList1 = new ArrayList<>();
        seccionUiList1.add(new SeccionUi(1, "A", institutoSanMiguelUi));
        seccionUiList1.add(new SeccionUi(2, "B", institutoSanMiguelUi));
        seccionUiList1.add(new SeccionUi(3, "C", institutoSanMiguelUi));
        seccionUiList1.add(new SeccionUi(4, "D", institutoSanMiguelUi));
        institutoSanMiguelUi.setSeccionList(seccionUiList1);
        institutoUis.add(institutoSanMiguelUi);

        InstitutoUi institutoAngelGabrielUi = new InstitutoUi();
        institutoAngelGabrielUi.setNombre("INSTITUTO SUPERIOR TENOLOGICO");
        institutoAngelGabrielUi.setImage("https://www.logrosperu.com/instituciones/logos/Logo_instituto-santa-rosa_6.jpg");
        institutoAngelGabrielUi.setCede("Lima");
        institutoAngelGabrielUi.setDireccion("Av. Próceres de la Independencia 3684");
        List<SeccionUi> seccionUiList2 = new ArrayList<>();
        seccionUiList2.add(new SeccionUi(1, "A", institutoAngelGabrielUi));
        seccionUiList2.add(new SeccionUi(2, "B", institutoAngelGabrielUi));
        seccionUiList2.add(new SeccionUi(3, "C", institutoAngelGabrielUi));
        seccionUiList2.add(new SeccionUi(4, "D", institutoAngelGabrielUi));
        institutoAngelGabrielUi.setSeccionList(seccionUiList2);
        institutoUis.add(institutoAngelGabrielUi);

        InstitutoUi institutoSanCarlosUi = new InstitutoUi();
        institutoSanCarlosUi.setNombre("16480 ANTONIO RAYMONDI");
        institutoSanCarlosUi.setImage("https://1.bp.blogspot.com/-Tgmkh6Jt4So/V32dr5k9bRI/AAAAAAAAyuM/blymlyDOUhQpuidyfVxqA0r2sSe7KPnawCLcB/s1600/ie-16480-antonio-raimondi.jpg");
        institutoSanCarlosUi.setCede("Cajamarca");
        institutoSanCarlosUi.setDireccion("Rumipite Bajo");
        List<SeccionUi> seccionUiList3 = new ArrayList<>();
        seccionUiList3.add(new SeccionUi(1, "A", institutoSanCarlosUi));
        seccionUiList3.add(new SeccionUi(2, "B", institutoSanCarlosUi));
        seccionUiList3.add(new SeccionUi(3, "C", institutoSanCarlosUi));
        seccionUiList3.add(new SeccionUi(4, "D", institutoSanCarlosUi));
        institutoSanCarlosUi.setSeccionList(seccionUiList3);
        institutoUis.add(institutoSanCarlosUi);


        InstitutoUi institutoBritanicoUi = new InstitutoUi();
        institutoBritanicoUi.setNombre("Colegio Peruano - Británico");
        institutoBritanicoUi.setImage("https://3.bp.blogspot.com/-pQVzRkJlywM/V-F7GOixA-I/AAAAAAAA00U/wcDoAHpyFBw_Xz8HhpT9Sbsa1pkYeqoagCLcB/s1600/ie-peruano-britanico-surco-insignia.jpg");
        institutoBritanicoUi.setCede("Lima");
        institutoBritanicoUi.setDireccion("Av. Vía Láctea 445 - Monterrico");
        List<SeccionUi> seccionUiList4 = new ArrayList<>();
        seccionUiList4.add(new SeccionUi(1, "A", institutoBritanicoUi));
        seccionUiList4.add(new SeccionUi(2, "B", institutoBritanicoUi));
        seccionUiList4.add(new SeccionUi(3, "C", institutoBritanicoUi));
        seccionUiList4.add(new SeccionUi(4, "D", institutoBritanicoUi));
        institutoBritanicoUi.setSeccionList(seccionUiList4);
        institutoUis.add(institutoBritanicoUi);

        return institutoUis;


    }
}
