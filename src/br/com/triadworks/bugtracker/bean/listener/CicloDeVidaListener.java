package br.com.triadworks.bugtracker.bean.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class CicloDeVidaListener implements PhaseListener{

	private static final long serialVersionUID = 869290597308913768L;

	@Override
	public void afterPhase(PhaseEvent e) {
		System.out.println("[-] Depois da fase: "+e.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent e) {
		System.out.println("[+] Antes da fase: "+e.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
