package com.list.server.controllers.secures;

import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Market;
import com.list.server.domain.entities.Shop;
import com.list.server.domain.entities.User;
import com.list.server.models.dtos.InvoiceDTO;
import com.list.server.models.dtos.UserDTO;
import com.list.server.models.requests.InvoiceRequestDTO;
import com.list.server.services.InvoiceService;
import com.list.server.services.MarketService;
import com.list.server.services.ShopService;
import com.list.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/invoice")
@RequiredArgsConstructor
@Component("secureInvoiceController")
public class InvoiceController {

    private final InvoiceService service;

    private final MarketService marketService;
    private final ShopService shopService;
    private final UserService userService;

    @GetMapping("/read/{id}")
    public List<InvoiceDTO> readById(@PathVariable("id") Long id) {
        List<Invoice> invoices = this.service.getByUserId(id);
        List<InvoiceDTO> invoiceDTOS = invoices.stream().map(InvoiceDTO::mapFromEntity).toList();
        return invoiceDTOS;
    }

    @PostMapping("/create")
    public InvoiceRequestDTO create(@RequestBody InvoiceRequestDTO invoiceDTO) {

        Market market = this.marketService.getById(invoiceDTO.marketId());
        Shop shop = this.shopService.getById(invoiceDTO.marketId());
        User user = this.userService.getById(invoiceDTO.userId());

        Invoice invoice = InvoiceRequestDTO.mapFromEntity(invoiceDTO, market, shop, user);

        return this.service.add(invoice, invoiceDTO);
    }

    @PutMapping("/update/{id}")
    public Invoice update(@RequestBody Invoice invoice, @PathVariable("id") Long id) {
        return this.service.edit(invoice, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            String invoiceDeleted = this.service.remove(id);
            return new ResponseEntity<>(invoiceDeleted, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            String errorMsg = "This id: '" + id + "' was not founded.";
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
        }
    }
}
