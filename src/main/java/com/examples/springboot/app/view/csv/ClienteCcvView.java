package com.examples.springboot.app.view.csv;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.examples.springboot.app.models.entity.Cliente;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("listar.csv")
public class ClienteCcvView extends AbstractView {
	
	public ClienteCcvView() {
		setContentType("text/csv");
	}

	@Override
	protected boolean generatesDownloadContent() {
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"clientes.csv\"");
		response.setContentType(getContentType());
		
		
		Page<Cliente> clientes =(Page<Cliente>) model.get("clientes");
		
		ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] header = {"id","nombre","apellido","email","createAt"};
		beanWriter.writeHeader(header);
		
		for (Cliente cliente: clientes) {
			beanWriter.write(cliente, header);	
		}
		
		beanWriter.close();
	}

}
