package com.systemvv.grupo.asitenciaapp.padre.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.padre.dataSource.HijosDataSource;
import com.systemvv.grupo.asitenciaapp.padre.dataSource.HijosRepository;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Instituto;

import java.util.List;

public class ObtenerInstituto extends UseCase<ObtenerInstituto.RequestValues, ObtenerInstituto.ResponseValue> {

    private HijosRepository hijosRepository;

    public ObtenerInstituto(HijosRepository hijosRepository) {
        this.hijosRepository = hijosRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
      hijosRepository.onObtenerInstituto(requestValues.getUsuarioUi(), new HijosDataSource.onCallBackResultado<Instituto>() {
          @Override
          public void onResult(Instituto resultado) {
              getUseCaseCallback().onSuccess(new ResponseValue(resultado));
          }
      });
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

    public static final class ResponseValue implements UseCase.ResponseValue {
        Instituto instituto;

        public ResponseValue(Instituto instituto) {
            this.instituto = instituto;
        }

        public Instituto getInstituto() {
            return instituto;
        }
    }
}
