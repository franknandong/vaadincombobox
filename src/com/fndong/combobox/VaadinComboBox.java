package com.fndong.combobox;

import java.util.ArrayList;
import java.util.List;

import com.fndong.combobox.domain.RefDistrict;
import com.fndong.combobox.domain.RefState;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author fndong.wordpress.com
 * date : 16/06/2016
 * About : vaadin - How create a second Combobox which is dependent from the First Combobox.
 */
@SuppressWarnings("serial")
@Theme("vaadincombobox")
public class VaadinComboBox extends UI {
	
	private BeanItemContainer<RefDistrict> objectsDistrict = new BeanItemContainer<>(RefDistrict.class);
	
	private ComboBox combofirst;   // first combobox
	 private ComboBox combosecond; // Second combobox
	 
	private List<RefState> refState = new ArrayList<RefState>();
	private List<RefDistrict> refDistrict = new ArrayList<RefDistrict>();
	 
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		
		GridLayout grid = new GridLayout(3,1);
		Button btnSubmit = new Button("Submit");
		combofirst= createFirstComboSelect(); // state combobox
		combosecond = createSecondComboSelect(); // district combobox
		grid.setSpacing(true);
		grid.setSizeFull();
		grid.setSizeUndefined();
		grid.addComponent(combofirst);
		grid.addComponent(combosecond);
		grid.addComponent(btnSubmit);
		// java.lang.IllegalArgumentException: Component must be added to layout before using setComponentAlignment()
		grid.setComponentAlignment(btnSubmit,Alignment.BOTTOM_LEFT);
		layout.addComponent(grid);
	}
	
	private ComboBox createFirstComboSelect() { // state combobox
		refState = getRefState(); // get records state dummy
         BeanItemContainer<RefState> objects = new BeanItemContainer<>(RefState.class, refState);
         ComboBox s = new ComboBox("RefState", objects);
        s.setItemCaptionPropertyId("statename");
        s.setImmediate(true);

   /*     s.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				 objectsDistrict.addAll( findDistrictSelection((RefState) event.getProperty().getValue()));
			}
		});*/

		// add lambda expression - java 8 
		s.addValueChangeListener((ValueChangeListener)
				(ValueChangeEvent) -> {
				  try {
					  combosecond.removeAllItems();
					  objectsDistrict.addAll(findDistrictSelection((RefState) ValueChangeEvent.getProperty().getValue()));
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						 Notification.show("Error on Selection Records",
		                            Type.ERROR_MESSAGE);
					}
				});
		
		return s;
	}
	
	private ComboBox createSecondComboSelect() {
		
    	ComboBox districtcombo = new ComboBox("refDistrict"); // combox box District with objects values
    	districtcombo.setValue("district");
    	districtcombo.setContainerDataSource(objectsDistrict);
    	// Allow adding new items
    	districtcombo.setItemCaptionPropertyId("refdistrictname");
    	districtcombo.setNewItemsAllowed(true);
    	districtcombo.setImmediate(true);
    	districtcombo.addStyleName("tiny");
    	districtcombo.setWidth("10em");
    	districtcombo.setFilteringMode(FilteringMode.CONTAINS);
    	districtcombo.setInvalidAllowed(false);
    	districtcombo.setNullSelectionAllowed(false);
    	 return districtcombo;
    }
	
	 private List<RefDistrict> findDistrictSelection(RefState refstate) {
	    	refDistrict = getRefDistrict(); // get all records district
	    	BeanItemContainer<RefDistrict> objects = new BeanItemContainer<>(RefDistrict.class, refDistrict);
	    	
	    	List<RefDistrict> objDistrictList = new ArrayList<RefDistrict>();
	    	 // how to get value same with the selection of object by filtering string refstate id
		     Object[] ids = objects.getItemIds().toArray();
		     System.out.println("length 1 : " + ids.length);
		  
		     for (int i = 0; i < ids.length; i++) {
		         if (((RefDistrict) ids[i]).getStateid().equals(refstate.getStateid())) {
		        	 System.out.println("scedule id : " + ((RefDistrict) ids[i]).getRefdistrictid()); // district id
		        	 System.out.println("schedule name : " + ((RefDistrict) ids[i]).getRefdistrictname()); // district name
		        	 objDistrictList.add((RefDistrict) ids[i]);
		        	 break;
		         }
		     }
		     return objDistrictList;
	    }
	
	// dummy data from state using entity refState
    private List<RefState> getRefState() {
    	refState.add(new RefState(new String("01"),"Malaysia"));
    	refState.add(new RefState(new String("02"),"Indonesia"));
    	refState.add(new RefState(new String("03"),"Vietnam"));
    	refState.add(new RefState(new String("04"),"Singapore"));
    	refState.add(new RefState(new String("05"),"Thailand"));
    return refState;
    }
    
    
    //dummy data from District using entity refDistrict
    private List<RefDistrict> getRefDistrict() {
    	refDistrict.add(new RefDistrict(new String("01"),"Kuala Lumpur","01"));
    	refDistrict.add(new RefDistrict(new String("02"),"Bandung","02"));
    	refDistrict.add(new RefDistrict(new String("03"),"Ho chi Minch city","03"));
    	refDistrict.add(new RefDistrict(new String("04"),"Jurong","04"));
    	refDistrict.add(new RefDistrict(new String("05"),"Phuket","05"));
    return refDistrict;
    }

}