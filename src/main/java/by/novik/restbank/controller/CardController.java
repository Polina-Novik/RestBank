package by.novik.restbank.controller;


import by.novik.restbank.dto.CardInformationResponse;
import by.novik.restbank.dto.ClientInformationResponse;


import by.novik.restbank.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Tag(name = "controller", description = "this is my controller with transfer object pattern")
@RequiredArgsConstructor
@RestController
@RequestMapping("card")
public class CardController {
    private final CardService cardService;

    @PostMapping("client")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find information about client", description = "This method returns name and " +
            "surname if client is present",
            responses = {@ApiResponse(responseCode = "200",
                    description = "client's information:",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientInformationResponse.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"name\": \"Ben\",\n" +
                                    "  \"surname\": \"Smith\"\n" +
                                    "}")
                    )}),
                    @ApiResponse(responseCode = "404",
                            description = "client not found", content = @Content)
            })
    public ClientInformationResponse findClientInformation(@Parameter(name = "cardNumber",
            description = "Enter card number", required = true) @RequestParam Long cardNumber, @Parameter(name
            = "password", description = "Enter the password of your personal account", required =
            true) @RequestParam String password) {
        return cardService.findClientInformation(cardNumber, password);
    }


    @PostMapping("information")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find information about card", description = "This method returns name, surname, " +
            "card number, card validity period, card balance if card is present",
            responses = {@ApiResponse(responseCode = "200",
                    description = "card information:",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardInformationResponse.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"cardNumber\": 1111111111111111,\n" +
                                    "  \"name\": \"Ben\",\n" +
                                    "  \"surname\": \"Smith\",\n" +
                                    "  \"date\": \"02/23\",\n" +
                                    "  \"sum\": 1000\n" +
                                    "}")
                    )}),
                    @ApiResponse(responseCode = "404",
                            description = "card not found", content = @Content)
            })
    public CardInformationResponse findCardInformation(@Parameter(name = "cardNumber",
            description = "Enter card number", required = true) @RequestParam Long cardNumber,
                                                       @Parameter(name = "password",
                                                               description = "Enter the password of your personal account", required = true)
                                                       @RequestParam String password) {
        return cardService.findCardInformation(cardNumber, password);
    }

    @PostMapping("pay")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Card payment", description = "This is a payment method if card is present",
            responses = {@ApiResponse(responseCode = "200",
                    description = "card information:",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardInformationResponse.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"cardNumber\": 1111111111111111,\n" +
                                    "  \"name\": \"Ben\",\n" +
                                    "  \"surname\": \"Smith\",\n" +
                                    "  \"date\": \"02/23\",\n" +
                                    "  \"sum\": 1000\n" +
                                    "}")
                    )}),
                    @ApiResponse(responseCode = "404",
                            description = "card not found", content = @Content)
            })
    public CardInformationResponse payByCard(@Parameter(name = "cardNumber",
            description = "Enter card number", required = true) @RequestParam Long cardNumber,
                                             @Parameter(name = "password",
                                                     description = "Enter the password of your personal account", required = true)
                                             @RequestParam String password, @Parameter(name = "price",
            description = "Payment amount", required = true) @RequestParam int price) {
        return cardService.payByCard(cardNumber, password, price);
    }

    @PostMapping("remittance")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Remittance", description = "This method is needed to transfer money from " +
            "one card to another if card is present",
            responses = {@ApiResponse(responseCode = "200",
                    description = "card information:",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardInformationResponse.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"cardNumber\": 1111111111111111,\n" +
                                    "  \"name\": \"Ben\",\n" +
                                    "  \"surname\": \"Smith\",\n" +
                                    "  \"date\": \"02/23\",\n" +
                                    "  \"sum\": 1000\n" +
                                    "}")
                    )}),
                    @ApiResponse(responseCode = "404",
                            description = "card not found", content = @Content)
            })
    public CardInformationResponse remittance(@Parameter(name = "cardNumber1",
            description = "Enter your card number", required = true)
                                              @RequestParam Long cardNumber1,
                                              @Parameter(name = "password1",
                                                      description = "Enter the password of your personal account", required = true)
                                              @RequestParam String password1, @Parameter(name = "price",
            description = "Transfer amount", required = true)
                                              @RequestParam int price, @Parameter(name = "cardNumber2",
            description = "Enter the card number to which you want to transfer money", required = true)
                                              @RequestParam Long cardNumber2) {
        return cardService.remittance(cardNumber1, password1, price, cardNumber2);
    }

}
