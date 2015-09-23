module.exports = function(app, settings) {
  var nodemailer = require('nodemailer').createTransport(settings);

  function * send(body) {
    nodemailer.sendMail(body);
  }

  function * gen_token(str) {
    var base64_email = new Buffer(str).toString('base64'),
      uniq_string = require('crypto').randomBytes(32).toString('hex');
    return base64_email + uniq_string;
  }

  function * send_approve_message(fio, email, mail_token) {
    var mail_approve_link = 'http://localhost:3000/auth/approve/' + mail_token;

    var mail_message = 'Это письмо от приложения Web App.';
    mail_message += ' Кликните на ссылку далее, чтобы потвердить электронную почту';
    mail_message += ' <a href="' + mail_approve_link + '">Закончить регистрацию</a>';

    yield send({
      from: 'Web App <geakstrasu@gmail.com>',
      to: fio + '<' + email + '>',
      subject: 'Welcome mail!',
      text: mail_message,
      html: mail_message
    });
  }

  app.context.mailer = {
    send: send,
    gen_token: gen_token,
    send_approve_message: send_approve_message
  };

}