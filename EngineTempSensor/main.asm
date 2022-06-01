;
; EngineTempSensor.asm
;
; Created: 12/9/2021 4:28:30 PM
; Author : Mark Guerin, Bane Lausterer, Gilbert Green
; Purpose: Simulates the engine temperature sensor of a vehicle, turning on a red light when hot, a blue light when cold, and a warning light when hot for too long


.ORG    0x00                  ; set up vector tables
          JMP    MAIN
.ORG    0x02
          JMP    COLD    
.ORG    0x04
          JMP    HOT
.ORG    INT_VECTORS_SIZE

MAIN:
                              ; GPIO setup
          CBI    DDRD,DDD2    ; cold temperature button input
          CBI    PORTD,PD2    ; high impedance
          CBI    DDRD,DDD3    ; hot temperature button input
          CBI    PORTD,PD3    ; high impedance
          SBI    DDRB,DDB1    ; hot temperature light output
          CBI    PORTB,PB1
          SBI    DDRB,DDB2    ; cold temperature light output
          CBI    PORTB,PB2
          SBI    DDRB,DDB3    ; warning light output
          CBI    PORTB,PB3
                              ; d for button, b for light

          LDI    R20,0x03     ; enable ext interrupt 1 and 2 for rising edge
          STS    EIMSK,R20
          LDI    R20,0x0F
          STS    EICRA,R20
          SEI                 ; enable global interrupts

                              ; set up Timer1 in CTC mode for 4 seconds
          CLR    R20
          STS    TCNT1H,R20
          STS    TCNT1L,R20
          LDI    R20,0xF4
          STS    OCR1AH,R20
          LDI    R20,0x23
          STS    OCR1AL,R20
          CLR    R20
          STS    TCCR1A,R20
    

AGAIN:                        ; loop until interrupt happens
          RJMP AGAIN

HOT:                          ; ISR 1: high temp
          LDI    R20,0x0D     ; Start timer
          STS    TCCR1B,R20
          SBIS   TIFR1,OCF1A  ; skip next instruction if TOV1 is high
          SBI    PORTB,PB3    ; turn on warning LED
          SBI    PORTB,PB1    ; turn on red LED
          SBIC   PIND,PIND3   ; check if temp is still high
          RJMP   HOT          ; if still high, go back to start of ISR
          CLR    R20          ; if no longer hot, stop timer
          STS    TCCR1B,R20
          STS    TCNT1H,R20   ; reset timer
          STS    TCNT1L,R20
          SBI    TIFR1,OCF1A
          CBI    PORTB,PB1    ; turn off red LED
          RETI

COLD:                         ; ISR 2: cold temp
          SBI    PORTB,PB2    ; turn on blue LED
          SBIC   PIND,PIND2   ; check if temp is still low
          RJMP   COLD         ; if still low, go back to start of ISR
          CBI    PORTB,PB2    ; if no longer cold, turn off blue LED
          RETI
