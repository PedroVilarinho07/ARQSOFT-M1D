| Element    | Statement |
| -------- | ------- |
| Stimulus           | The software does not use any IAM, and does not provide variability to login according to different IAM's requested.|
| Stimulus Source    | The current login mechanism uses simple authentication, which is unsafe. Users must register manually, increasing maintenance effort and development complexity. |
| Environment        | The login and registration are directly implemented within the software. |
| Artifact           | ACME |
| Response           | The system should apply an IAM provider at runtime. |
| Response Measure   | The system can authenticate using an IAM provider, and it should be possible to change the IAM provider during runtime via configuration. |