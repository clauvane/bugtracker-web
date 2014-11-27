package br.com.triadworks.bugtracker.bean.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.triadworks.bugtracker.bean.UsuarioLogado;

public class AutorizacaoListener implements PhaseListener{

	private static final long serialVersionUID = 2131261966382807861L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		FacesContext facesContext = arg0.getFacesContext();
		String paginaAcessada = facesContext.getViewRoot().getViewId();
		if ("/pages/usuario/login.xhtml".equals(paginaAcessada)) {
			return;
		}
		
		UsuarioLogado usuarioLogado = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{usuarioLogado}", UsuarioLogado.class);
		if (!usuarioLogado.isLogado()) {
			NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
			handler.handleNavigation(facesContext, null, "/pages/usuario/login.xhtml?faces-redirect=true");
			facesContext.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
