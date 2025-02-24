import fr.eql.ai116.linus.wattelse.business.BankAccountBusiness;
import fr.eql.ai116.linus.wattelse.business.CreditCardBusiness;
import fr.eql.ai116.linus.wattelse.business.exceptions.BankAccountException;
import fr.eql.ai116.linus.wattelse.business.exceptions.CreditCardException;
import fr.eql.ai116.linus.wattelse.entity.dto.BankAccountDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.BankAccount;
import fr.eql.ai116.linus.wattelse.entity.pojo.CreditCard;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/paymentMethods")
public class PaymentMethodsController {

    @EJB
    CreditCardBusiness creditCardBusiness;

    @EJB
    BankAccountBusiness bankAccountBusiness;

    @POST
    @Path("/addCreditCard")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCreditCard(CreditCard creditCardDto) {
        try {
            creditCardBusiness.addCreditCard(creditCardDto);
            return Response.ok().build();
        } catch (CreditCardException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @POST
    @Path("/addBankAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBankAccount(BankAccount bankAccount) {
        try {
            bankAccountBusiness.addBankAccount(bankAccount);
            return Response.ok().build();
        } catch (BankAccountException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("/fetch/creditCard/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchCreditCards(@PathParam("id") Long id) {
        List<fr.eql.ai116.linus.wattelse.entity.dto.CreditCardDto> creditCardDtoDtos = creditCardBusiness.fetchUsersCreditCard(id);
        if (creditCardDtoDtos == null || creditCardDtoDtos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No credit cards found for user with ID: " + id)
                    .build();
        }
        return Response.ok(creditCardDtoDtos).build();
    }

    @GET
    @Path("/fetch/bankaccount/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchBankAccount(@PathParam("id") Long id) {
        System.out.println("Ok in");
        BankAccountDto bankAccountDto = bankAccountBusiness.fetchUsersBankAccount(id);
        if (bankAccountDto == null ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No bank account found for user with ID: " + id)
                    .build();
        }
        return Response.ok(bankAccountDto).build();
    }
}
