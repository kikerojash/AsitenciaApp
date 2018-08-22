package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource.TareaGlobalDataSource;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource.TareaGlobalRepository;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.entidad.TareasGlobales;

import java.util.List;

public class RegistrarTareaGlobales extends UseCase<RegistrarTareaGlobales.RequestValues,RegistrarTareaGlobales.ResponseValue> {

    TareaGlobalRepository tareaGlobalRepository;

    public RegistrarTareaGlobales(TareaGlobalRepository tareaGlobalRepository) {
        this.tareaGlobalRepository = tareaGlobalRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        tareaGlobalRepository.onGuardarTareasGlobales(requestValues.getTareasGlobalesList(), requestValues.getDescripcionTareaGobal(), new TareaGlobalDataSource.CallBackResultad<Boolean>() {
            @Override
            public void onCallResultado(Boolean resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        Boolean aBoolean;

        public ResponseValue(Boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public Boolean getaBoolean() {
            return aBoolean;
        }
    }

    public static final class RequestValues implements UseCase.RequestValues{
        List<TareasGlobales>tareasGlobalesList;
        String descripcionTareaGobal;

        public RequestValues(List<TareasGlobales> tareasGlobalesList, String descripcionTareaGobal) {
            this.tareasGlobalesList = tareasGlobalesList;
            this.descripcionTareaGobal = descripcionTareaGobal;
        }

        public List<TareasGlobales> getTareasGlobalesList() {
            return tareasGlobalesList;
        }

        public String getDescripcionTareaGobal() {
            return descripcionTareaGobal;
        }
    }
}
