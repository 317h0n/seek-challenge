# RSA Public & Private Keys

We have to create a public / private key pair. We need to have **[OpenSSL][openssl]** installed in our systems.

## Create Certs

First, we have to create rsa key pair

```sh
openssl genrsa -out keypair.pem 2048
```

Then, we have to extract public key

```sh
openssl rsa -in keypair.pem -pubout -out public.pem
```

Finally, we have to create private key in PKCS#8 format

```sh
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```

If the process was successful you can delete **keypair.pem** file.

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[openssl]: <https://openssl-library.org/source/index.html>
