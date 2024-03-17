package services;

import java.util.HashMap;
import java.util.Map;

import DTO.PostPartyBody;
import entities.Party;
import entities.exceptions.BusinessException;
import entities.exceptions.Errors;

public class PartyService implements PartyServiceInterface {

	private Map<Integer, Party> parties = new HashMap<Integer, Party>();
	private Map<String, Party> partyByDoc = new HashMap<String, Party>();

	@Override
	public Party getParty(Integer partyId) {
		return parties.get(partyId);
	}

	@Override
	public int postParty(PostPartyBody body) {
		if(!isCPF(body.getDocumentNumber())) {
			throw new BusinessException(Errors.INVALID_DOCUMENT.getErrorMessage(),Errors.INVALID_DOCUMENT.getErrorCode());
		}
		int partyId = 1;
		
		if (!parties.isEmpty()) {
			partyId = parties.size() + 1;
		}
		
		if(partyExists(body.getDocumentNumber())) {
			
			throw new BusinessException(Errors.PARTY_ALREADY_EXISTS.getErrorMessage(),
					Errors.PARTY_ALREADY_EXISTS.getErrorCode());
			
		} else {
			Party newParty = new Party(body.getName(), partyId, body.getDocumentNumber());
			parties.put(partyId, newParty);
			
			relatePartyAndDocument(newParty);
			
			return partyId;
		}		
	}
	
	@Override
	public Party getPartyByDocument(String documentNumber) {
		if(!isCPF(documentNumber)) {
			throw new BusinessException(Errors.INVALID_DOCUMENT.getErrorMessage(),Errors.INVALID_DOCUMENT.getErrorCode());
		}
		if (partyByDoc.isEmpty()) {
			throw new BusinessException(Errors.NO_PARTY_REGISTERED.getErrorMessage(),
					Errors.NO_PARTY_REGISTERED.getErrorCode());
			
		}
		if (!partyByDoc.containsKey(documentNumber)) {
			
			throw new BusinessException(Errors.NO_PARTY_REGISTERED.getErrorMessage(),
					Errors.NO_PARTY_REGISTERED.getErrorCode());
			
		}
		return partyByDoc.get(documentNumber);
	}
	
	private void relatePartyAndDocument(Party newParty) {
		partyByDoc.put(newParty.getDocumentNumber(), newParty);
	}
	
	private boolean partyExists(String documentNumber) {
		try {
		
			getPartyByDocument(documentNumber);
		
			return true;
		}
		catch (BusinessException e) {
			if(e.getErrorCode() == Errors.NO_PARTY_REGISTERED.getErrorCode()) {
				return false;
			} else {
				throw e;
			}
		}
	}
	
	private static boolean isCPF(String CPF) {

        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;
        
        sm = 0;
        peso = 10;
        for (i=0; i<9; i++) {
   
        num = (int)(CPF.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig10 = '0';
        else dig10 = (char)(r + 48); 

        sm = 0;
        peso = 11;
        for(i=0; i<10; i++) {
        num = (int)(CPF.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
             dig11 = '0';
        else dig11 = (char)(r + 48);

        if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
             return(true);
        else return(false);
               
        }

	

}
