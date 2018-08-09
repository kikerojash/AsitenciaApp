package com.systemvv.grupo.asitenciaapp.padre.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.padre.dataSource.HijosDataSource;
import com.systemvv.grupo.asitenciaapp.padre.dataSource.HijosRepository;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;

import java.util.List;

public class ObtenerMisHijos extends UseCase<ObtenerMisHijos.RequestValues, ObtenerMisHijos.ResponseValue> {


    private HijosRepository hijosRepository;

    public ObtenerMisHijos(HijosRepository hijosRepository) {
        this.hijosRepository = hijosRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        hijosRepository.onObtenerMisHijos(requestValues.getUsuarioUi(), new HijosDataSource.onCallBackResultado<List<Hijos>>() {
            @Override
            public void onResult(List<Hijos> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<Hijos> hijosList;

        public ResponseValue(List<Hijos> hijosList) {
            this.hijosList = hijosList;
        }

        public List<Hijos> getHijosList() {
            return hijosList;
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {
        UsuarioUi usuarioUi;

        public RequestValues(UsuarioUi usuarioUi) {
            this.usuarioUi = usuarioUi;
        }

        public UsuarioUi getUsuarioUi() {
            return usuarioUi;
        }
    }
}
