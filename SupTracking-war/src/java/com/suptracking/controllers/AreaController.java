package com.suptracking.controllers;

import com.suptracking.entity.Area;
import com.suptracking.entity.Car;
import com.suptracking.service.AreaService;
import com.suptracking.service.CarService;
import com.suptracking.utils.Utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

/**
 * @author EPTR
 */
@ManagedBean
@ViewScoped
public class AreaController implements Serializable {
    private static final String urlManageAreas = "/user/manageAreas.xhtml?faces-redirect=true";
    private final String viewId;
    private Long idAreaToEdit;
    private Area area;
    private DataModel<Area> listAreas;
    private List<Car> listCarsOfUser;
    @EJB
    private AreaService areaService;
    @EJB
    private CarService carService;

    public AreaController() {
        viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        area = null;
        listAreas = null;
        listCarsOfUser = null;
    }

    @PostConstruct
    private void init() {
        if (viewId.contains("formArea")) {
            String paramId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            idAreaToEdit = Utils.convertStringToLong(paramId);
            listCarsOfUser = carService.getCarsOfUser(Utils.getUserInSession());
            if (idAreaToEdit == null) {
                area = new Area();
            } else {
                area = areaService.findAreaById(idAreaToEdit);
            }
        } else if (viewId.contains("manageAreas")) {
            listAreas = new ListDataModel(areaService.getAreasOfUser(Utils.getUserInSession()));
        }
    }

    public Area getArea() {
        return area;
    }

    public DataModel<Area> getListAreas() {
        return listAreas;
    }

    public Long getIdAreaToEdit() {
        return idAreaToEdit;
    }

    public void setIdAreaToEdit(Long idAreaToEdit) {
        this.idAreaToEdit = idAreaToEdit;
    }

    public List<Car> getListCarsOfUser() {
        return listCarsOfUser;
    }

    public String addArea() {
        area.getGpsPos().setArea(area);
        areaService.addArea(area);
        return urlManageAreas;
    }

    public String editArea() {
        areaService.updateArea(area);
        return urlManageAreas;
    }

    public String removeArea() {
        areaService.removeArea(listAreas.getRowData().getId());
        return urlManageAreas;
    }
}